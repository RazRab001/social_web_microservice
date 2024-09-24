import { Request, Response } from 'express';
import {v4 as uuidv4} from 'uuid';
import bcrypt from 'bcrypt'
import { RequestWithBody, RequestWithParams } from '../dto/request.type';
import { UserSign, userDataInput, userInfoDto } from '../dto/userDto';
import UserService from '../service/user.service';
import tokenService from '../service/token.service';
import userService from '../service/user.service';
import * as dotenv from 'dotenv'
dotenv.config()

class SecurityController{
    async registation(req: RequestWithBody<UserSign>, res: Response){
        let {email, password} = req.body
        const hash_pass = await bcrypt.hash(password, 7);
        const activationLink = uuidv4();
        
        const exist_user = await UserService.getUserByLogin(email)
        if(exist_user){
            return res.status(400).json({message: "Email already exist"})
        }

        const new_user = await UserService.createNewUser({login: email, password: hash_pass}, activationLink)
        if(new_user == null){
            return res.status(500).json({message: "Fatal errror on user creation"})
        }
        const userDto = new userInfoDto(new_user)
        const tokens = await tokenService.createTokens(userDto)
        await userService.sendEmailActivation(userDto.login!, activationLink)

        return res.status(201).json({user: userDto, tokens: tokens})
    }

    async login(req: RequestWithBody<UserSign>, res: Response){
        let {email, password} = req.body

        const exist_user = await UserService.getUserByLogin(email);
        if(!exist_user){
            return res.status(400).json({message: "User with this email doesn't exist"})
        }
        if(!exist_user.isActive){
            return res.status(400).json({message: "User with this email doesn't active"})
        }


        if(!await bcrypt.compare(password, exist_user.password as string)){
            return res.status(400).json({message: "Wrong password"})
        }
        
        const userDto = new userInfoDto(exist_user);
        const tokens = await tokenService.createTokens(userDto);

        return res.status(201).json({user: userDto, tokens: tokens})
    }

    async activate(req: Request, res: Response){
        const link = req.params.link
        const activated = await userService.activateUser(link)
        if(activated){
            console.log("redirect")
            return res.status(302).redirect(`http://${process.env.APP_HOST}:${process.env.APP_PORT}`)
        } else {
            return res.status(404).json({message: "Activation is failed"})
        }
    }
}

export = new SecurityController()
import jwt, { JwtPayload } from "jsonwebtoken"
import dotenv from "dotenv"
import { userInfoDto } from "../dto/userDto";

class TokenService{
    createTokens(userDto: userInfoDto){
        const access_token = jwt.sign({userDto}, process.env.JWT_ACCESS_SECRET as string, {expiresIn: '20m'});
        const refresh_token = jwt.sign({userDto}, process.env.JWT_REFRESH_SECRET as string, {expiresIn: '1d'});
        return {
            access_token,
            refresh_token,
        }
    }
}

export = new TokenService()
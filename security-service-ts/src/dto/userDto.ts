import { User } from "../entity/User"

export type userDataInput = {
    login: string,
    password: string
}

export type UserSign = {
    email: string,
    password: string
}

export class userInfoDto{
    id?: number
    login?: string

    constructor(user: User){
        this.id = user.id
        this.login = user.login
    }
}
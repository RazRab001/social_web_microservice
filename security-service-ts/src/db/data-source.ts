import "reflect-metadata"
import { DataSource } from "typeorm"
import { User } from "../entity/User"
import * as dotenv from 'dotenv'
dotenv.config()

export const AppDataSource = new DataSource({
    type: "postgres",
    host: "localhost",
    port: process.env.POSTGRES_PORT as unknown as number || 5432,
    username: "postgres",
    password: "admin",
    database: "security-db",
    synchronize: true,
    logging: false,
    entities: [User],
    migrations: [],
    subscribers: [],
})

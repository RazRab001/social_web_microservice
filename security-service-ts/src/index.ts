import express from 'express'
import SecurityRouter from './router/security.route'
import * as dotenv from 'dotenv'
import cors from 'cors'
import 'reflect-metadata';
import { createConnection } from 'typeorm';
dotenv.config()

const app = express()
const port = process.env.APP_PORT || 3000
const host = process.env.APP_HOST || 'localhost'

app.use(cors())
app.use(express.json())
app.use('/auth', SecurityRouter)

createConnection().then(async connection => {
    console.log('Connected to the database');

    app.listen(port, ()=>{
        console.log(`security server running on http://${host}:${port}`)
    })
})

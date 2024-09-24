import express from 'express'
import * as dotenv from 'dotenv'
import cors from 'cors'
import 'reflect-metadata';
import { Consumer, KafkaClient, Message, Topic } from 'kafka-node';
import mailService from './service/mail.service';
dotenv.config()

const app = express()
const port = process.env.APP_PORT || 3000
const host = process.env.APP_HOST || 'localhost'

app.use(cors())
app.use(express.json())

const topics: Topic[] = [
    { topic: 'email-requests' }
];

const kafkaClient = new KafkaClient({ kafkaHost: process.env.KAFKA_BROKER_LIST });
const kafkaConsumer = new Consumer(kafkaClient, topics, {
    groupId: 'activation-group',
});

kafkaConsumer.on('message', async (message: Message) => {
    const payload = JSON.parse(message.value as string);
    const { email, activationLink } = payload;
    console.log("Kafka catch the message ", payload)

    try {
        await mailService.sendMail(email, activationLink);
    } catch (error) {
        console.error('Error sending email:', error);
    }
});

kafkaConsumer.on('error', (err: Error) => {
    console.error('Error in Kafka consumer:', err);
});

app.listen(port, ()=>{
    console.log(`mail server running on http://${host}:${port}`)
})

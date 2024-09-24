import { Connection, createConnection } from 'typeorm';
import { User } from '../entity/User';
import { userDataInput } from '../dto/userDto';
import { KafkaClient, Producer } from 'kafka-node';
import * as dotenv from 'dotenv';
import { UUID } from 'crypto';
import axios from 'axios';

dotenv.config();

class UserService {
    private connection!: Connection
    private kafkaProducer: Producer;

    constructor() {
        this.init();

        const kafkaClient = new KafkaClient({ kafkaHost: process.env.KAFKA_BROKER_LIST });
        this.kafkaProducer = new Producer(kafkaClient);
        this.kafkaProducer.on('ready', () => {
            console.log('Kafka producer is ready');
        });
        this.kafkaProducer.on('error', (err: Error) => {
            console.error('Error in Kafka producer:', err);
        });
    }

    async sendEmailActivation(email: string, activationLink: string) {
        const payload = {
            email,
            activationLink: `http://${process.env.APP_HOST}:${process.env.APP_PORT}/auth/activate/${activationLink}`,
        };
        console.log(email, activationLink)
        const payloads = [{ topic: 'email-requests', messages: JSON.stringify(payload) }];
        this.kafkaProducer.send(payloads, (err, data) => {
            if (err) {
                console.error('Error sending message to Kafka:', err);
            } else {
                console.log('Message sent to Kafka:', payloads);
            }
        });
    }

    private async init() {
        try {
            this.connection = await createConnection();
            console.log('Database connection initialized');
        } catch (error) {
            console.error('Error initializing database connection:', error);
            throw error;
        }
    }

    async createNewUser(data: userDataInput, activationLink: string): Promise<User | null> {
        try {
            const userRepository = this.connection.getRepository(User);
            const newUser = userRepository.create({
                login: data.login,
                password: data.password,
                activationLink: activationLink
            });
            return await userRepository.save(newUser);
        } catch (error) {
            console.error('Error creating user:', error);
            throw error;
        }
    }

    async loginUser(data: userDataInput): Promise<User | null> {
        try {
            const userRepository = this.connection.getRepository(User);
            return await userRepository.findOne({
                where: {
                    isActive: true,
                    login: data.login,
                    password: data.password,
                },
            });
        } catch (error) {
            console.error('Error logging in user:', error);
            throw error;
        }
    }

    async getUserByLogin(login: string): Promise<User | null> {
        try {
            const userRepository = this.connection.getRepository(User);
            return await userRepository.findOne({
                where: {
                    login: login,
                },
            });
        } catch (error) {
            console.error('Error retrieving user by login:', error);
            throw error;
        }
    }

    async activateUser(link: string){
        try {
            const userRepository = this.connection.getRepository(User);
            let user = await userRepository.findOne({
                where: {
                    activationLink: link
                }
            });
    
            if (!user) {
                return false;
            }
    
            user.isActive = true;
            user = await userRepository.save(user);
            //return user ? true : false;
            if (user) {
                const url = `http://localhost:8080/api/v1/account/${user.id}`;
                const response = await axios.post(url);
                console.log("GET RESPONSE ", response)
                return response.status === 201;
            }
    
            return false;
    
        } catch (error) {
            console.error('Error user activate:', error);
            throw error;
        }
    }
}

export default new UserService();

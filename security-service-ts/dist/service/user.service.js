"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
const typeorm_1 = require("typeorm");
const User_1 = require("../entity/User");
const kafka_node_1 = require("kafka-node");
const dotenv = __importStar(require("dotenv"));
dotenv.config();
class UserService {
    constructor() {
        this.init();
        const kafkaClient = new kafka_node_1.KafkaClient({ kafkaHost: process.env.KAFKA_BROKER_LIST });
        this.kafkaProducer = new kafka_node_1.Producer(kafkaClient);
        this.kafkaProducer.on('ready', () => {
            console.log('Kafka producer is ready');
        });
        this.kafkaProducer.on('error', (err) => {
            console.error('Error in Kafka producer:', err);
        });
    }
    sendEmailActivation(email, activationLink) {
        return __awaiter(this, void 0, void 0, function* () {
            const payload = {
                email,
                activationLink,
            };
            const payloads = [{ topic: 'email-requests', messages: JSON.stringify(payload) }];
            this.kafkaProducer.send(payloads, (err, data) => {
                if (err) {
                    console.error('Error sending message to Kafka:', err);
                }
                else {
                    console.log('Message sent to Kafka:', data);
                }
            });
        });
    }
    init() {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                this.connection = yield (0, typeorm_1.createConnection)();
                console.log('Database connection initialized');
            }
            catch (error) {
                console.error('Error initializing database connection:', error);
                throw error;
            }
        });
    }
    createNewUser(data) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const userRepository = this.connection.getRepository(User_1.User);
                const newUser = userRepository.create({
                    login: data.login,
                    password: data.password,
                });
                return yield userRepository.save(newUser);
            }
            catch (error) {
                console.error('Error creating user:', error);
                throw error;
            }
        });
    }
    loginUser(data) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const userRepository = this.connection.getRepository(User_1.User);
                return yield userRepository.findOne({
                    where: {
                        login: data.login,
                        password: data.password,
                    },
                });
            }
            catch (error) {
                console.error('Error logging in user:', error);
                throw error;
            }
        });
    }
    getUserByLogin(login) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const userRepository = this.connection.getRepository(User_1.User);
                return yield userRepository.findOne({
                    where: {
                        login: login,
                    },
                });
            }
            catch (error) {
                console.error('Error retrieving user by login:', error);
                throw error;
            }
        });
    }
}
exports.default = new UserService();

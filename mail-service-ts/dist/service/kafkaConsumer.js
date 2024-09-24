"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const kafka_node_1 = require("kafka-node");
const dotenv_1 = __importDefault(require("dotenv"));
const mail_service_1 = __importDefault(require("./mail.service"));
dotenv_1.default.config();
const topics = [
    { topic: 'email-requests' }
];
const kafkaClient = new kafka_node_1.KafkaClient({ kafkaHost: process.env.KAFKA_BROKER_LIST });
const kafkaConsumer = new kafka_node_1.Consumer(kafkaClient, topics, {
    groupId: 'my-consumer-group',
});
kafkaConsumer.on('message', (message) => __awaiter(this, void 0, void 0, function* () {
    const payload = JSON.parse(message.value);
    const { email, activationLink } = payload;
    console.log('Kafka consumer catch data', payload);
    try {
        yield mail_service_1.default.sendMail(email, activationLink);
    }
    catch (error) {
        console.error('Error sending email:', error);
        // Handle the error as needed, e.g., log, retry, etc.
    }
}));
kafkaConsumer.on('error', (err) => {
    console.error('Error in Kafka consumer:', err);
});

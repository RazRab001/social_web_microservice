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
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (Object.hasOwnProperty.call(mod, k)) result[k] = mod[k];
    result["default"] = mod;
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const dotenv = __importStar(require("dotenv"));
const cors_1 = __importDefault(require("cors"));
require("reflect-metadata");
const kafka_node_1 = require("kafka-node");
const mail_service_1 = __importDefault(require("./service/mail.service"));
dotenv.config();
const app = express_1.default();
const port = process.env.APP_PORT || 3000;
const host = process.env.APP_HOST || 'localhost';
app.use(cors_1.default());
app.use(express_1.default.json());
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
    console.log("Kafka catch the message ", payload);
    try {
        yield mail_service_1.default.sendMail(email, `http://${host}:${port}/auth/activate/${activationLink}`);
    }
    catch (error) {
        console.error('Error sending email:', error);
    }
}));
kafkaConsumer.on('error', (err) => {
    console.error('Error in Kafka consumer:', err);
});
app.listen(port, () => {
    console.log(`security server running on http://${host}:${port}`);
});

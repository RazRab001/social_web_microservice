"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
const bcrypt_1 = __importDefault(require("bcrypt"));
const userDto_1 = require("../dto/userDto");
const user_service_1 = __importDefault(require("../service/user.service"));
const token_service_1 = __importDefault(require("../service/token.service"));
class SecurityController {
    registation(req, res) {
        return __awaiter(this, void 0, void 0, function* () {
            let { email, password } = req.body;
            const hash_pass = yield bcrypt_1.default.hash(password, 7);
            const exist_user = yield user_service_1.default.getUserByLogin(email);
            if (exist_user) {
                return res.status(400).json({ message: "Email already exist" });
            }
            const new_user = yield user_service_1.default.createNewUser({ login: email, password: hash_pass });
            if (new_user == null) {
                return res.status(500).json({ message: "Fatal errror on user creation" });
            }
            const userDto = new userDto_1.userInfoDto(new_user);
            const tokens = yield token_service_1.default.createTokens(userDto);
            return res.status(201).json({ user: userDto, tokens: tokens });
        });
    }
    login(req, res) {
        return __awaiter(this, void 0, void 0, function* () {
            let { email, password } = req.body;
            const exist_user = yield user_service_1.default.getUserByLogin(email);
            if (!exist_user) {
                return res.status(400).json({ message: "User with this email doesn't exist" });
            }
            if (!(yield bcrypt_1.default.compare(password, exist_user.password))) {
                return res.status(400).json({ message: "Wrong password" });
            }
            const userDto = new userDto_1.userInfoDto(exist_user);
            const tokens = yield token_service_1.default.createTokens(userDto);
            return res.status(201).json({ user: userDto, tokens: tokens });
        });
    }
}
module.exports = new SecurityController();

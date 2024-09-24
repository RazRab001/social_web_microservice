"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
const jsonwebtoken_1 = __importDefault(require("jsonwebtoken"));
class TokenService {
    createTokens(userDto) {
        const access_token = jsonwebtoken_1.default.sign({ userDto }, process.env.JWT_ACCESS_SECRET, { expiresIn: '20m' });
        const refresh_token = jsonwebtoken_1.default.sign({ userDto }, process.env.JWT_REFRESH_SECRET, { expiresIn: '1d' });
        return {
            access_token,
            refresh_token,
        };
    }
}
module.exports = new TokenService();

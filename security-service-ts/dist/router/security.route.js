"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
const express_1 = require("express");
const express_validator_1 = require("express-validator");
const ErrorsCatcher_1 = require("./ErrorsCatcher");
const security_controller_1 = __importDefault(require("../controller/security.controller"));
const router = (0, express_1.Router)();
const RegistrationInput = [
    (0, express_validator_1.body)('email').isEmail(),
    (0, express_validator_1.body)('password').isStrongPassword({ minLength: 7, minLowercase: 2, minSymbols: 1 }),
];
router.post('/reg', RegistrationInput, ErrorsCatcher_1.CatchValidationErrors, security_controller_1.default.registation);
router.post('/login', RegistrationInput, ErrorsCatcher_1.CatchValidationErrors, security_controller_1.default.login);
module.exports = router;

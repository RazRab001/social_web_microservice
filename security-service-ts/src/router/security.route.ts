import {Router} from 'express'
import { body } from 'express-validator'
import { CatchValidationErrors } from './ErrorsCatcher'
import securityController from '../controller/security.controller'
const router = Router()

const RegistrationInput = [
    body('email').isEmail(),
    body('password').isStrongPassword({minLength: 7, minLowercase: 2, minSymbols: 1}),
]

router.post('/reg', RegistrationInput, CatchValidationErrors, securityController.registation)
router.post('/login', RegistrationInput, CatchValidationErrors, securityController.login)
router.get('/activate/:link', securityController.activate)

export = router
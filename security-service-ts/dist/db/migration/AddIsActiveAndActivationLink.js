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
Object.defineProperty(exports, "__esModule", { value: true });
exports.AddIsActiveAndActivationLinkToUser1688765432100 = void 0;
class AddIsActiveAndActivationLinkToUser1688765432100 {
    constructor() {
        this.name = 'AddIsActiveAndActivationLinkToUser1688765432100';
    }
    up(queryRunner) {
        return __awaiter(this, void 0, void 0, function* () {
            yield queryRunner.query(`ALTER TABLE "user" ADD "isActive" boolean NOT NULL DEFAULT false`);
            yield queryRunner.query(`ALTER TABLE "user" ADD "activationLink" character varying`);
        });
    }
    down(queryRunner) {
        return __awaiter(this, void 0, void 0, function* () {
            yield queryRunner.query(`ALTER TABLE "user" DROP COLUMN "activationLink"`);
            yield queryRunner.query(`ALTER TABLE "user" DROP COLUMN "isActive"`);
        });
    }
}
exports.AddIsActiveAndActivationLinkToUser1688765432100 = AddIsActiveAndActivationLinkToUser1688765432100;

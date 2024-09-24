import { MigrationInterface, QueryRunner } from "typeorm";

export class AddIsActiveAndActivationLinkToUser1688765432100 implements MigrationInterface {
    name = 'AddIsActiveAndActivationLinkToUser1688765432100'

    public async up(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.query(`ALTER TABLE "user" ADD "isActive" boolean NOT NULL DEFAULT false`);
        await queryRunner.query(`ALTER TABLE "user" ADD "activationLink" character varying`);
    }

    public async down(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.query(`ALTER TABLE "user" DROP COLUMN "activationLink"`);
        await queryRunner.query(`ALTER TABLE "user" DROP COLUMN "isActive"`);
    }
}

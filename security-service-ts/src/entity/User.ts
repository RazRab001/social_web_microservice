import { Entity, PrimaryGeneratedColumn, Column, Index } from "typeorm"

@Entity()
export class User {

    @PrimaryGeneratedColumn()
    id!: number

    @Column()
    @Index({ unique: true })
    login!: string

    @Column()
    password!: string

    @Column()
    isActive: boolean = false

    @Column()
    activationLink!:string
}

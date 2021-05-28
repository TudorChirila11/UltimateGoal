package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous (name="autonomie")
public class Autonomie extends LinearOpMode {
    ///Motors
    DcMotor rightBackWheel;
    DcMotor rightFrontWheel;
    DcMotor leftBackWheel;
    DcMotor leftFrontWheel;
    DcMotor intake;
    DcMotor shooter;
    CRServo lift;
    CRServo push;

    ///Motor Power
    double rightBackWheelPower;
    double rightFrontWheelPower;
    double leftBackWheelPower;
    double leftFrontWheelPower;
    double intakePower;
    double shooterPower;
    double liftPower;
    double pushPower;

    ///Others
    double modifier;

    public void init1() {
        leftBackWheel = hardwareMap.get(DcMotor.class, "leftBackWheel");
        rightBackWheel = hardwareMap.get(DcMotor.class, "rightBackWheel");
        leftFrontWheel = hardwareMap.get(DcMotor.class, "leftFrontWheel");
        rightFrontWheel = hardwareMap.get(DcMotor.class, "rightFrontWheel");

        rightBackWheel.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFrontWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBackWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFrontWheel.setDirection(DcMotorSimple.Direction.FORWARD);

        intake = hardwareMap.get(DcMotor.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);

        shooter = hardwareMap.get(DcMotor.class, "shooter");
        shooter.setDirection(DcMotorSimple.Direction.FORWARD);

        lift = hardwareMap.get(CRServo.class, "lift");
        lift.setDirection(CRServo.Direction.FORWARD);

        push = hardwareMap.get(CRServo.class, "push");
        push.setDirection(CRServo.Direction.FORWARD);
    }
    public void misca(double speed)
    {
        leftBackWheel.setPower(speed);
        leftFrontWheel.setPower(speed);
        rightBackWheel.setPower(speed);
        rightFrontWheel.setPower(speed);
    }
    public void roteste(double speed)
    {
        leftBackWheel.setPower(speed);
        leftFrontWheel.setPower(speed);
        rightBackWheel.setPower(-speed);
        rightFrontWheel.setPower(-speed);
    }
    public void misca_brat()
    {
        push.setPower(-1);
        sleep(200);
        push.setPower(1);
        sleep(200);
        push.setPower(0);
    }
    @Override
    public void runOpMode()
    {
        init1();
        waitForStart();
       /* misca(0.5);
        sleep(3000);
        misca(0);
        roteste(1);
        sleep(1500);
        roteste(0);
        shooter.setPower(-1);
        sleep(3000);
        misca_brat();
        shooter.setPower(0);*/
    }
}

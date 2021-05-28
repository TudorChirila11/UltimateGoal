package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import java.awt.font.NumericShaper;

@TeleOp(name="misca te")

public class GamepadDriveTeleOp extends OpMode {


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
    double modifier1;
    double modifier2;

    @Override
    public void init() {
        leftBackWheel = hardwareMap.get(DcMotor.class, "leftBackWheel");
        rightBackWheel = hardwareMap.get(DcMotor.class, "rightBackWheel");
        leftFrontWheel = hardwareMap.get(DcMotor.class, "leftFrontWheel");
        rightFrontWheel = hardwareMap.get(DcMotor.class, "rightFrontWheel");

        rightBackWheel.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFrontWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBackWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFrontWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        intake = hardwareMap.get(DcMotor.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);

        shooter = hardwareMap.get(DcMotor.class, "shooter");
        shooter.setDirection(DcMotorSimple.Direction.FORWARD);

        lift = hardwareMap.get(CRServo.class, "lift");
        lift.setDirection(CRServo.Direction.FORWARD);

        push = hardwareMap.get(CRServo.class, "push");
        push.setDirection(CRServo.Direction.FORWARD);
    }

    @Override
    public void loop() {

            if (gamepad1.left_bumper) modifier1 = 0.5;
            else modifier1 = 1.0;
            telemetry.addData( "mod1", modifier1);

            if (gamepad2.left_bumper) modifier2 = 0.5;
            else modifier2 = 1.0;
            telemetry.addData( "mod2", modifier2);

            ///Motor Directions (left joystick)
            rightFrontWheelPower = gamepad1.left_stick_y - gamepad1.left_stick_x;
            leftFrontWheelPower = -gamepad1.left_stick_y - gamepad1.left_stick_x;
            rightBackWheelPower = -gamepad1.left_stick_y - gamepad1.left_stick_x;
            leftBackWheelPower = gamepad1.left_stick_y - gamepad1.left_stick_x;

            ///Turning with trigger
            if(gamepad1.right_trigger != 0 || gamepad1.left_trigger != 0) {
                rightFrontWheelPower = -gamepad1.right_trigger + gamepad1.left_trigger;
                rightBackWheelPower = -gamepad1.right_trigger + gamepad1.left_trigger;
                leftBackWheelPower = gamepad1.right_trigger - gamepad1.left_trigger;
                leftFrontWheelPower = gamepad1.right_trigger - gamepad1.left_trigger;
            }

            ///Clipping
            rightFrontWheelPower = Range.clip(rightFrontWheelPower, -1, 1);
            leftFrontWheelPower = Range.clip(leftFrontWheelPower, -1, 1);
            rightBackWheelPower = Range.clip(rightBackWheelPower, -1, 1);
            leftBackWheelPower = Range.clip(leftBackWheelPower, -1, 1);

            ///Apllaying Modifiers
            rightFrontWheelPower = rightFrontWheelPower * modifier1;
            leftFrontWheelPower = leftFrontWheelPower * modifier1;
            rightBackWheelPower = rightBackWheelPower * modifier1;
            leftBackWheelPower = leftBackWheelPower * modifier1;

            ///Start Motors
            rightFrontWheel.setPower(rightFrontWheelPower);
            rightBackWheel.setPower(rightBackWheelPower);
            leftBackWheel.setPower(leftBackWheelPower);
            leftFrontWheel.setPower(leftFrontWheelPower);

            ///Intake



            intakePower = ((gamepad2.x ? 1.0 : 0.0) - (gamepad2.b ? 1.0 : 0.0)) * modifier2;
            intake.setPower(intakePower);

            ///Shooter
            shooterPower = (gamepad2.a?1.0:0.0);
            shooter.setPower(shooterPower);

            ///Lift
            liftPower = gamepad2.right_stick_y;
            lift.setPower(liftPower);

            ///Push
            if(gamepad2.dpad_up)
                push.setPower(1);
            else if(gamepad2.dpad_down)
                push.setPower(-1);
            else push.setPower(0);

            ///Debugging
            telemetry.addData("rightFront",rightFrontWheelPower);
            telemetry.addData("rightBack", rightBackWheelPower);
            telemetry.addData("leftBack", leftBackWheelPower);
            telemetry.addData("leftFront", leftFrontWheelPower);
            telemetry.addData("gamepad1.left_stick_y", gamepad1.left_stick_y);
            telemetry.addData("gamepad1.left_stick_x", gamepad1.left_stick_x);
            telemetry.addData( "intake", intake.getPower());
            telemetry.addData( "intake", intakePower);
            telemetry.addData( "shooter", shooterPower);
            telemetry.addData("lift" , liftPower);
            telemetry.addData("push" , push.getPower());

            ///Emergency button reverse intake
    }
}

package org.firstinspires.ftc.teamcode.useless;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.useless.Hardware;

//Intoarcere cu bumpere
@TeleOp(name="Miscare2", group="Miscare")
@Disabled
public class Miscare2 extends OpMode{

    Hardware robot = new Hardware();
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {

        double G1X = -gamepad1.right_stick_x;
        double G1Y = -gamepad1.right_stick_y;
        boolean right_bumber = gamepad1.right_bumper;
        boolean left_bumper = gamepad1.left_bumper;

        if(right_bumber) {
            robot.mfl.setPower(0.5);
            robot.mbl.setPower(0.5);
            robot.mfr.setPower(-0.5);
            robot.mbr.setPower(-0.5);
        }
        else if(left_bumper){
            robot.mfr.setPower(0.5);
            robot.mbr.setPower(0.5);
            robot.mfl.setPower(-0.5);
            robot.mbl.setPower(-0.5);
        }
        else {
            double directie = Math.atan2(G1Y, G1X);
            double magnitudine = Math.sqrt(G1X * G1X + G1Y * G1Y);
            double pi=3.14;
            double putere1 = Math.sin(directie - pi / 4) * magnitudine;
            double putere2 = Math.sin(directie + pi / 4) * magnitudine;

            robot.mfr.setPower(putere1);
            robot.mbl.setPower(putere1);
            robot.mfl.setPower(putere2);
            robot.mbr.setPower(putere2);
        }
    }
}
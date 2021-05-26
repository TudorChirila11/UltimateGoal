package org.firstinspires.ftc.teamcode.useless;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.useless.Hardware;

//Intoarcere in timpul miscarii
@TeleOp(name="Miscare3", group="Miscare")
@Disabled
public class Miscare3 extends OpMode{

    Hardware robot = new Hardware();
    double pi = Math.PI;

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {

        double G1X = -gamepad1.right_stick_x;
        double G1Y = -gamepad1.right_stick_y;
        double turn = -gamepad1.left_stick_x;

        double directie = Math.atan2(G1Y, G1X);
        double magnitudine = Math.sqrt(G1X * G1X + G1Y * G1Y);

        double puterefl = Math.sin(directie + pi / 4) * magnitudine + turn;
        double puterefr = Math.sin(directie - pi / 4) * magnitudine - turn;
        double puterebl = Math.sin(directie - pi / 4) * magnitudine + turn;
        double puterebr = Math.sin(directie + pi / 4) * magnitudine - turn;
        double factor;

        if (Math.abs(puterebl) > 1 || Math.abs(puterebr) > 1 || Math.abs(puterefr) > 1 || Math.abs(puterefl) > 1){
            factor = Math.max(Math.max(Math.abs(puterebl), Math.abs(puterebr)), Math.max(Math.abs(puterefl), Math.abs(puterefr)));
            puterebl = puterebl / factor;
            puterebr = puterebr / factor;
            puterefl = puterefl / factor;
            puterefr = puterefr / factor;
        }

        robot.mfr.setPower(puterefr);
        robot.mbl.setPower(puterebl);
        robot.mfl.setPower(puterefl);
        robot.mbr.setPower(puterebr);
    }
}
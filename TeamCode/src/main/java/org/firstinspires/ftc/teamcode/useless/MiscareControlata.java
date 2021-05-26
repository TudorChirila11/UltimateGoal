package org.firstinspires.ftc.teamcode.useless;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.useless.Hardware;

@TeleOp(name="Pushbot: Teleop Tank", group="Pushbot")
@Disabled
public class MiscareControlata extends OpMode{

    Hardware robot = new Hardware();

    @Override
    public void init() {
            robot.init(hardwareMap);
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        double G1LeftStickY = -gamepad1.left_stick_y;
        double G1RightStickY = -gamepad1.right_stick_y;
        boolean G1LeftBumper = gamepad1.right_bumper;
        boolean G1RightBumper = gamepad1.left_bumper;

        if(G1LeftBumper){
            robot.mfl.setPower(-1);
            robot.mfr.setPower(-1);
            robot.mbl.setPower(1);
            robot.mbr.setPower(1);
        }
        else if (G1RightBumper){
            robot.mfl.setPower(1);
            robot.mfr.setPower(1);
            robot.mbl.setPower(-1);
            robot.mbr.setPower(-1);
        }
        else{
            robot.mfl.setPower(G1LeftStickY);
            robot.mbl.setPower(G1LeftStickY);
            robot.mfr.setPower(G1RightStickY);
            robot.mbr.setPower(G1RightStickY);
        }
    }

    @Override
    public void stop() {
    }
}

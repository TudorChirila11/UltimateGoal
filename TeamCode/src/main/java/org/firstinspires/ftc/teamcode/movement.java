/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.util.*;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;


@TeleOp(name="misca din buric")
public class movement extends LinearOpMode {
    // Declare OpMode members.
    public DcMotor mfl;
    public DcMotor mfr;
    public DcMotor mbl;
    public DcMotor mbr;
    public DcMotor intake, shooter, rulment;
    public CRServo lift, push;
    public void turn(double speedturn)
    {
        mfl.setPower(speedturn);
        mfr.setPower(speedturn);
        mbl.setPower(-speedturn);
        mbr.setPower(-speedturn);
    }
    double[] vectorToDCMotorsPower(double[] direction) throws ArithmeticException {
        double angle = Math.atan2(direction[1], direction[0]) * 57.2957795f; // Angle in Deg
        angle -= 45; // Rotate with 45 deg
        angle *= 0.0174532925f;
        double dlen=Math.sqrt(Math.abs(direction[0])*Math.abs(direction[0])+Math.abs(direction[1])*Math.abs(direction[1]));
        double[] ans={Math.cos(angle) * dlen, Math.sin((angle) * dlen)};
        return ans;
    }
    void moveState(double x, double y, double rotation, double speed) {
        double[] newv={x,y};
        double[] p = vectorToDCMotorsPower(newv);
        double fl1 = p[0] + rotation;
        double fr1 = p[1] - rotation;
        double bl1 = p[1] + rotation;
        double br1 = p[0] - rotation;
        //if (!activateMotorsAndServos)
          //  return;
        mfl.setPower(Range.clip(fl1, -1, 1) * speed);
        mfr.setPower(Range.clip(fr1, -1, 1) * speed);
        mbl.setPower(Range.clip(bl1, -1, 1) * speed);
        mbr.setPower(Range.clip(br1, -1, 1) * speed);
    }
    public void move_intake(double speed) {
        intake.setPower(speed);
    }
    public void shoot()
    {
        if(gamepad2.a)
            shooter.setPower(1);
    }
    public void hardwaremapping()
    {
        mfl = hardwareMap.get(DcMotor.class, "mfl");
        mfr = hardwareMap.get(DcMotor.class,"mfr");
        mbl = hardwareMap.get(DcMotor.class,"mbl");
        mbr = hardwareMap.get(DcMotor.class,"mbr");
        shooter=hardwareMap.get(DcMotor.class, "shooter");
        intake=hardwareMap.get(DcMotor.class, "intake");
        lift=hardwareMap.crservo.get("lift");
        push=hardwareMap.crservo.get("push");
        rulment=hardwareMap.get(DcMotor.class,"rulment");
    }
    public void init1()
    {
        mfl.setDirection(DcMotorSimple.Direction.FORWARD);
        mfr.setDirection(DcMotorSimple.Direction.FORWARD);
        mbl.setDirection(DcMotorSimple.Direction.FORWARD);
        mbr.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        lift.setDirection(DcMotorSimple.Direction.FORWARD);
        push.setDirection(DcMotorSimple.Direction.FORWARD);
        rulment.setDirection(DcMotorSimple.Direction.FORWARD);
        mfl.setPower(0);
        mfr.setPower(0);
        mbl.setPower(0);
        mbr.setPower(0);
        shooter.setPower(0);
        intake.setPower(0);
        lift.setPower(0);
        push.setPower(0);
        rulment.setPower(0);
    }
    @Override
    public void runOpMode() {
        hardwaremapping();
        init1();
        shooter.setDirection(DcMotorSimple.Direction.FORWARD);
        lift.setDirection(DcMotorSimple.Direction.FORWARD);
        push.setDirection(DcMotorSimple.Direction.FORWARD);
        waitForStart();
        while(opModeIsActive())
        {
            double x=-gamepad1.left_stick_x;
            double y=-gamepad1.left_stick_y;
            double speedturn = gamepad1.right_trigger-gamepad1.left_trigger;
            moveState(x,y,speedturn,5);
            double intakespeed=-gamepad2.left_stick_y;
            move_intake(intakespeed);
            shoot();
            if(gamepad2.dpad_up)
                lift.setPower(-1);
            else lift.setPower(0);
            if(gamepad2.dpad_down)
                lift.setPower(1);
            else lift.setPower(0);
            if(gamepad2.dpad_left)
                push.setPower(-1);
            else push.setPower(0);
            if(gamepad2.dpad_right)
                push.setPower(1);
            else push.setPower(0);
            double rulmentpower=gamepad2.right_stick_x;
            rulment.setPower(rulmentpower);
        }
    }
}
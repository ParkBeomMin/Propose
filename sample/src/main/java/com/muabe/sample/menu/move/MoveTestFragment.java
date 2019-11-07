package com.muabe.sample.menu.move;

import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.Button;

import com.markjmind.uni.UniFragment;
import com.markjmind.uni.common.Jwc;
import com.markjmind.uni.mapper.annotiation.GetView;
import com.markjmind.uni.mapper.annotiation.Layout;
import com.muabe.propose.Propose;
import com.muabe.propose.action.SingleTouchRightGesture;
import com.muabe.propose.Motion;
import com.muabe.propose.player.Player;
import com.muabe.propose.player.animation.ObjectAnimatorPlugIn;
import com.muabe.sample.R;

@Layout(R.layout.move_test)
public class MoveTestFragment extends UniFragment{
    @GetView
    Button button, button2, button3;

    Motion motionRight;
    Player combinationPlayer;

    float ratio = 0f;
    @Override
    public void onPost() {
        float maxMove = 150f* Jwc.getDensity(button);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(button, View.ROTATION_Y, 360);
        ObjectAnimator right = ObjectAnimator.ofFloat(button, "translationX", maxMove);

        motionRight = new Motion(new SingleTouchRightGesture(maxMove));
//        final Player player = AnimationPlayer.create(10, right).setName("left");
//        Player player2 = AnimationPlayer.create(10, rotation).setName("rotation");
//        player.selfAnd(player2);
//
//        combinationPlayer = player.selfAnd(player2);

        final Player player = new Player(new ObjectAnimatorPlugIn(right));
        final Player player2 = new Player(new ObjectAnimatorPlugIn(rotation));
        combinationPlayer = player.next(player2);
        motionRight.setPlayer(combinationPlayer);


        Propose p = new Propose(getContext());
        p.setMotion(motionRight);
        button.setOnTouchListener(p);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                motionRight.play(motionRight.getRatio()+0.1f);
                ratio = ratio+ 0.2f;
                player2.play(ratio);
            }
        });
    }

}

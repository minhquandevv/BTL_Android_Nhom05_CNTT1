package com.tlu.btlandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import com.tlu.btlandroid.R;

import adapter.Fitnessadapter;
import untity.Fitness;

public class Activity_fitness extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView rcvFit;
    private Button btn1, btn2, btn3, btn4;
    private  GridLayoutManager grid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);

        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        rcvFit = findViewById(R.id.rcv_fit);

        grid = new GridLayoutManager(this,1);
        rcvFit.setLayoutManager(grid);

        Fitnessadapter adapter = new Fitnessadapter(getListFitness());
        rcvFit.setAdapter(adapter);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

    }

    private List<Fitness> getListFitness() {
        List<Fitness> list = new ArrayList<>();
        list.add(new Fitness("Fitness Basics", "So you have decided that you wish to take up exercising. This may be in the form of weight lifting, aerobics, fitness are all! Congratulations! You have taken the most important first step in towards the new you. Now the plan is to stick with it! There are many different reasons why people choose to incorporate physical fitness into their lives; whether it is simply to lose weight, gain size or to generally increase their well being. Whatever the multitude of reasons.. yours is equally as important as any. It must be a 100% individual effort and commitment thing however. You shouldn't put your body through stress for someone else. Once you truly believe that you are doing it for yourself, then you will feel motivated and the results will sweep you off your feet! Therefore, once again dedication and commitment are key variables for the duration of your goals and successes. There are basically two aspects of physical fitness which will allow you to attain your goals. These are exercise and nutrition. Both parts work together and suffer if not combined correctly. In other words, your training will be put to waste if you are not eating accordingly and likewise for your nutrition if you are not giving it your all at the gym. Therefore, a complete and balanced exercise regimen consist of the two to form a whole.",Fitness.TYPE_1));
        list.add(new Fitness("Basics Principles and Terminology","There are a few fundamental terms and principles which you must make yourself familiar with to be successful with. Consider these as the game rules as many other sports have. Abiding by the rules is a must! Below are the basics of basics to get you on your way.",Fitness.TYPE_2));
        list.add(new Fitness("","Once you have moved up to the advanced or high intermediate level, it is strongly recommended that you implement advanced training principles into your training! How do you know when you are at such a level? Well, generally speaking you will have made progress in muscle gain, strength and an overall greater understanding of your body and it's needs to progress. In terms of time, generally speaking, 3 to 5 years lifting experience is appropriate. Also, for those of you who have reached plateau's in your progress or gains, you should also use these principles to shock your muscles back into growth!",Fitness.TYPE_2));
        list.add(new Fitness("","The word reps is a shortening of repetitions. A Repetition is one complete movement of an exercise. This must be a complete range of motion of the exercise and must be performed correctly. When you deal with the word reps, you will come across strict and forced reps as well. ",Fitness.TYPE_2));
        list.add(new Fitness("","Sets are a collection of repetitions. This must be a consecutive number of repetitions. In addition, you will come across straight set which are regular sets; as well as pyramid sets which increase in weight by every set performed.",Fitness.TYPE_2));
        list.add(new Fitness("","The amount of rest you take between each set is important. Normally, while you are performing the same exercise, you should rest no more than 2 minutes between sets. However, if you are training with extra heavy weights, then rest between sets may be up to 5 minutes. Waiting too long between sets does not stimulate the muscles as effectively.",Fitness.TYPE_2));
        list.add(new Fitness("","You should always train your larger muscle groups before small ones. The main reason for this is that it takes more of your energy to train these larger muscle than the smaller ones. When it comes down to the order of training within small and large groups.. train your weakest first.",Fitness.TYPE_2));
        list.add(new Fitness("","Breathing patterns are also very important during your sets. As a rule to inhaling and exhaling air, remember to inhale are you lower the weight or perform the negative part of the movement, and exhale on the positive part. Please do not hold your breath.",Fitness.TYPE_2));
        list.add(new Fitness("Body Types","Your body type affects your fitness routine. Once you have identified your body type you can exercise more efficiently and effectively. Take a look at the descriptions below to see where you fit in. Then start getting more out of your fitness routine than ever before.",Fitness.TYPE_3));
        list.add(new Fitness("","The ectomorph is characterized by long arms and legs and a short upper body. They also have long and thin muscles. Ectomorphs usually have a very low fat storage; therefore they are usually referred to as skinny. An example would be a basketball player. The fact remains that ectomorphs have the most difficulty putting on muscle mass, therefore, that should be one of the main goals for this individual. The ectomorph should; use heavy sets and use low repetitions. One should also rest and eat plenty during the day, with an increase in total caloric intake. Ectomorphs can get away with eating fats since their fast metabolism burn it rapidly. It is important that you minimize or stop other aerobic activities such as running or other sports. Doing so will save your calories for crucial building of muscle tissue.",Fitness.TYPE_3));
        list.add(new Fitness("","Mesomorph The mesomorph is characterized by a good rate of muscle growth. They have large bones, solid torso combined with low fat levels. It is also noted that they have wide shoulders with a narrow waist. An example is an Olympic gymnast. The composition of the mesomorph provides the foundations for the best bodybuilding physique or individual seeking improvements in their physique. By keeping their fat levels down and their rate of muscle growth high, they are the ideal physique. Therefore, they will find it simple to build muscle, therefore they should take advantage of this trait and aim for a balanced physique. Mesomorphs should train with basic exercises and continue a balanced meal program. Outside aerobic activity should be kept at average.",Fitness.TYPE_3));
        list.add(new Fitness("","The endomorph is characterized by an increased amount of fat storage, due to having a large amount of fat cells than the average person. They have a wide waist and a large bone structure. An example is a power weight lifter. The training regimen is quite different from the previous two body types. The rep range for exercises should be kept high and training should be quick. The idea is to get rid of calories, which is what is difficult for endomorphs to do. Therefore, additional aerobic activity is recommended. Diet wise, once again a low calorie diet is a must. ",Fitness.TYPE_3));
        list.add(new Fitness("Benefits of Exercise","During our lives, the more we demand from our bodies - our muscles, heart, bones and lungs - the stronger and more physically fit they all will become. It is key to remember that when we do not keep active with out bodies then they begin to deteriorate. The benefits of physical fitness are both physical as well as mental. Staying physically fit also protects you from disease. Another key aspect is wellness, and keeping all aspects of it balanced. ",Fitness.TYPE_4));
        list.add(new Fitness("","Physical Benefits of Fitness: Increased life expectancy, Increased resistance in fatigue, Quicker recovery from illness and injury, Increased lean body mass -Decreased body fat, Improved cardiac function, Decreased risk of dying from cardiovascular disease, Control of blood pressure levels, Improved regulation of blood clotting, Strengthened tendons, ligaments, bones and muscle.",Fitness.TYPE_4));
        list.add(new Fitness("","Mental Health and Well-Being Benefits: Improved quality of life, Reduced symptoms of stress, Improved appearance and self-image, Tension relief, Increased energy levels",Fitness.TYPE_4));

        return list;

    }



    private void scrollToItem(int index) {
        if(grid == null){
            return;
        }
        grid.scrollToPositionWithOffset(index, 0);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_1) {
            scrollToItem(0);
        } else if (v.getId() == R.id.btn_2) {
            scrollToItem(1);
        }else if (v.getId() == R.id.btn_3) {
            scrollToItem(8);
        }else if (v.getId() == R.id.btn_4) {
            scrollToItem(12);
        }

    }
}

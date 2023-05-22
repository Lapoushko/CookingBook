package com.example.testjavaprojectpp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testjavaprojectpp.EatActivity.ActivityEat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    public static String nameCategory ="";

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ConstraintLayout constraintLayout = findViewById(R.id.second_layout);

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.anim_menu_circle);
        ImageView btn = (ImageView) findViewById(R.id.image_icon_circle);
        btn.startAnimation(anim);

        tv = (TextView) findViewById(R.id.author_text);
        newTypeWriterText();
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Typewriter writer = new Typewriter(tv);
//                writer.stopWriting();
//                newTypeWriterText();
//            }
//        });

        SearchView sv = (SearchView) findViewById(R.id.searcher);

        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
                nameCategory = "everyone";
                startActivity(intent2);
            }
        });
//        Button btn_add = findViewById(R.id.btnTestBd);
//        Button btn_add_read = findViewById(R.id.btnTestRead);
//
//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                RecipeModel recipeModel = new RecipeModel(-1,"Куринный суп","Куринная грудка","Пожарь","Суп","banan_oladii.jpg");
//                Toast.makeText(SecondActivity.this, recipeModel.toString(), Toast.LENGTH_SHORT).show();
//
//                DatabaseHelper databaseHelper = new DatabaseHelper(SecondActivity.this);
//
//                boolean success = databaseHelper.addOne(recipeModel);
//
//                Toast.makeText(SecondActivity.this,"Success= " + success, Toast.LENGTH_SHORT).show();
//            }
//        });
//        btn_add_read.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatabaseHelper databaseHelper = new DatabaseHelper(SecondActivity.this);
//                List<RecipeModel> everyone = databaseHelper.getEveryone("Суп");
//
//                Toast.makeText(SecondActivity.this,everyone.get(0).getRecipeName(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void clickGarnish(View view){
        Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
        nameCategory = "Горячее";
        startActivity(intent2);
    }

    public void clickSoups(View view){
        Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
        nameCategory = "Суп";
        startActivity(intent2);
    }
    public void clickSalads(View view){
        Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
        nameCategory = "Салаты";
        startActivity(intent2);
    }
    public void clickSnacks(View view){
        Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
        nameCategory = "Закуски";
        startActivity(intent2);
    }
    public void clickSauce(View view){
        Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
        nameCategory = "Соус";
        startActivity(intent2);
    }
    public void clickPizza(View view){
        Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
        nameCategory = "Пицца";
        startActivity(intent2);
    }
    public void clickDrinks(View view){
        Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
        nameCategory = "Напитки";
        startActivity(intent2);
    }
    public void clickDesert(View view){
        Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
        nameCategory = "Десерт";
        startActivity(intent2);
    }
    public void clickBreakfast(View view){
        Intent intent2 = new Intent(SecondActivity.this, ActivityEat.class);
        nameCategory = "Завтрак";
        startActivity(intent2);
    }

    public void clickPrivacyPolicy(View view){
        Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
        openURL.setData(Uri.parse("https://github.com/Lapoushko/CookingBook/blob/main/TestJavaProjectPP/Privacy%20policy.txt"));
        startActivity(openURL);
    }
    @Override
    public void onBackPressed() {
    }

    private Runnable mMyRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            //Change state here
        }
    };

    private void newTypeWriterText(){
        String[] arrayStr = {
                "Рецепты не работают, если вы не используете свое сердце. \n ©Дилан Джонс",
                "Хороший повар похож на волшебницу, которая раздает счастье. \n ©Эльза Скиапарелли.",
                "Вы то, что вы едите, так что ешьте хорошо. \n ©Шеф-повар Франческо.",
                "Кухня жесткая и формирует невероятно сильных персонажей. \n ©Гордон Рэмси",
                "Рецепт не имеет души. Вы, как повар, должны добавить свою душу в рецепт. \n ©Томас Келлер.",
                "Наслаждение блюдом должно быть незабываемым. \n ©Ален Дюкасс.",
                "Еда - один из самых важных аспектов жизни. \n ©Марко Пьер Уайт.",
                "Приготовление пищи - это как рисование или написание песни. \n ©Вольфганг Па",
                "Иногда в самые глубокие моменты нет слов. Есть только еда. \n ©Рой Чой",
                "Повара являются лидерами в своем маленьком мире. \n ©Эрик Риперт",
                "С едой можно играть. \n ©Эмерил Лагассе",
                "Если бы Бог хотел, чтобы мы следовали рецептам, он не дал бы нам бабушек. \n ©Линда Хенли",
                "Лучшие блюда самые простые. \n ©Аугуст Эскофье"

        };

        Random r = new Random();
        String textAuthor = arrayStr[r.nextInt(arrayStr.length)];
        Typewriter writer = new Typewriter(tv);
        writer.animateText(textAuthor);
    }

    public class Typewriter {

        private String sText = new String();
        private int index;
        private long mDelay = 50;

        TextView textView;
        Handler myHandler = new Handler();
        public Typewriter(TextView tView) {
            textView = tView;
        }

        public void animateText(String string) {
            sText = string;
            index = 0;

            textView.setText("");

            myHandler.removeCallbacks(characterAdder);
            myHandler.postDelayed(characterAdder, mDelay);

        }

        private Runnable characterAdder = new Runnable() {
            @Override
            public void run() {
                textView.setText(sText.subSequence(0, index++));

                if (index <= sText.length()) {
                    new Handler().postDelayed(characterAdder, mDelay);
                }
            }
        };
    }

}
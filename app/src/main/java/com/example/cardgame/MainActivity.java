package com.example.cardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CardGame cardGame;
    private TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int backImage = R.drawable.question;
        Card card1 = new Card(R.drawable.cow1);
        Card card2 = new Card(R.drawable.cow2);
        Card card3 = new Card(R.drawable.cow3);
        Card card4 = new Card(R.drawable.cow4);
        Card card5 = new Card(R.drawable.cow5);
        Card card6 = new Card(R.drawable.cow6);
        score = (TextView) findViewById(R.id.score);

        Card[] cards = {card1, card2, card3, card4, card5, card6};
        ArrayList<Card> cardArray = new ArrayList<>();
        for (int i = 0; i < cards.length; i++){
            cardArray.add(cards[i]);
            cardArray.add(cards[i]);
        }
        Collections.shuffle(cardArray);
        ImageView[] listOfImageView = new ImageView[12];
        listOfImageView[0] = (ImageView) findViewById(R.id.pic1);
        listOfImageView[1] = (ImageView) findViewById(R.id.pic2);
        listOfImageView[2] = (ImageView) findViewById(R.id.pic3);
        listOfImageView[3] = (ImageView) findViewById(R.id.pic4);
        listOfImageView[4] = (ImageView) findViewById(R.id.pic5);
        listOfImageView[5] = (ImageView) findViewById(R.id.pic6);
        listOfImageView[6] = (ImageView) findViewById(R.id.pic7);
        listOfImageView[7] = (ImageView) findViewById(R.id.pic8);
        listOfImageView[8] = (ImageView) findViewById(R.id.pic9);
        listOfImageView[9] = (ImageView) findViewById(R.id.pic10);
        listOfImageView[10] = (ImageView) findViewById(R.id.pic11);
        listOfImageView[11] = (ImageView) findViewById(R.id.pic12);

        cardGame = new CardGame(cardArray, listOfImageView,backImage);
    }

    @Override
    protected void onResume(){
        super.onResume();
        setViews();
        score.setText("Score: 0");
    }

    private void setViews(){
        for (ImageView imageView: cardGame.getListOfImageView()){
            imageView.setOnClickListener((view)->{
                int cardNumber = (int) imageView.getTag();
                cardGame.flipCard(imageView, cardNumber);
                if (cardGame.getNumCardOver() == 2){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            cardGame.checkResult();
                            score.setText("Score: " + cardGame.getScore());
                        }
                    }, 1000);
                }
            });
        }
    }



}

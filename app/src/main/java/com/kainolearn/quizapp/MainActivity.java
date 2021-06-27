package com.kainolearn.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView countLabel, questionLabel;
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;

    private String rightAnswer;
    private int rightAnswerCount;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 5;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String[][] quizData = {
            // {"都道府県名", "正解", "選択肢１", "選択肢２", "選択肢３"}
            {"お茶漬けの素に入っている「あられ」には、何の意味があるでしょうか？", "湿気予防のため", "食感が良いから", "香りが良いから", "色合いのため"},
            {"「シルバー人材センター」など、高齢者を表す色というイメージがある「銀（シルバー）」。\n" +
                    "\n" +
                    "高齢者を指す言葉として定着した由来は何でしょうか？", "優先席", "銀座", "銀歯", "白髪"},
            {"金色の折り紙は、銀色の折り紙にある色を塗ることで作られます。その色はなんでしょうか？", "オレンジ色", "白色", "黒色", "緑色"},
            {"ある高級ブランドがサッカーボールを作ったことがあります。どのブランドでしょうか？", "ルイヴィトン","エルメス", "プラダ", "ロレックス"},
            {"ことわざの「急がば回れ」の語源となったのはどこでしょうか？", "琵琶湖","鳥取砂丘", "清水寺", "富士山"},
            {"かぐや姫の原作である「竹取物語」の作者は誰でしょうか？", "作者不明","紫式部", "清少納言", "聖徳太子"},
            {"江戸時代には、身分が高い女性があることをしてしまった際に、「私がやりました」と身代わりになる人がいました。\n" +
                    "\n" +
                    "何の身代わりだったでしょうか？", "おなら","食い逃げ", "つまみ食い", "個人情報の流出"},
            {"「地震、雷、火事、親父」と怖いものを順に並べた言葉があります。\n" +
                    "\n" +
                    "この「親父」は元々自然災害を表していたという説がありますが、それは一体何でしょうか？", "台風","津波", "火山の噴火", "雪崩"},
            {"靴の一種、「ローファー」には英語でどんな意味があるでしょうか？", "怠け者","働き者", "親不孝者", "幸せ者"},
            {"日本では飛鳥時代から平安時代頃にかけて「蘇」という乳製品が作られていました。\n" +
                    "\n" +
                    "現在で言えば何に近い物でしょうか？", "チーズ","ヨーグルト", "ミルクココア", "生クリーム"},
            {"海上で事件や事故が起こった時、海上保安庁に通報する番号はどれでしょうか？\n" +
                    "\n", "118","104", "177", "189"},
            {"カーペットの掃除などに使われる「コロコロ」を開発した会社であるニトムズは、コロコロの発売以前、粘着テープの力である虫を捕まえる道具を発売しました。\n" +
                    "\n" +
                    "その虫とは一体なんでしょうか？", "ゴキブリ","ハエ", "蚊\n","ムカデ"},
            {"お寿司屋さんでは、醤油のことをある色の名前で呼びます。何と呼ぶでしょうか？", "ムラサキ","アカ", "ミドリ", "クロ"},
            {"ラー油の「ラー」とはどんな意味でしょうか？", "辛い","香ばしい", "赤い", "美味しい"},
    };

    String[][] quizData1 = {
            // {"都道府県名", "正解", "選択肢１", "選択肢２", "選択肢３"}
            {"北海道", "札幌市", "長崎市", "福島市", "前橋市"},
            {"青森県", "青森市", "広島市", "甲府市", "岡山市"},
            {"岩手県", "盛岡市","大分市", "秋田市", "福岡市"},
            {"宮城県", "仙台市", "水戸市", "岐阜市", "福井市"},
            {"秋田県", "秋田市","横浜市", "鳥取市", "仙台市"},
            {"山形県", "山形市","青森市", "山口市", "奈良市"},
            {"福島県", "福島市", "盛岡市", "新宿区", "京都市"},
            {"茨城県", "水戸市", "金沢市", "名古屋市", "奈良市"},
            {"栃木県", "宇都宮市", "札幌市", "岡山市", "奈良市"},
            {"群馬県", "前橋市", "福岡市", "松江市", "福井市"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countLabel = findViewById(R.id.countLabel);
        questionLabel = findViewById(R.id.questionLabel);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);

        // quizDataからクイズ出題用のquizArrayを作成する
        for (int i = 1; i < quizData.length; i++) {
            // 新しいArrayListを準備
            ArrayList<String> tmpArray = new ArrayList<>();

            // クイズデータを追加
            tmpArray.add(quizData[i][0]); // 都道府県
            tmpArray.add(quizData[i][1]); // 正解
            tmpArray.add(quizData[i][2]); // 選択肢1
            tmpArray.add(quizData[i][3]); // 選択肢2
            tmpArray.add(quizData[i][4]); // 選択肢3

            // tmpArrayをquizArrayに追加
            quizArray.add(tmpArray);
        }
        showNextQuiz();
    }

    public void showNextQuiz() {
        // クイズのカウントラベルを更新
        countLabel.setText(getString(R.string.count_label, quizCount));

        // ランダムな数字を取得
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        // randomNumを使ってquizArrayからクイズを一つ取り出す
        ArrayList<String> quiz = quizArray.get(randomNum);

        // 問題文（都道府県）を表示
        questionLabel.setText(quiz.get(0));

        // 正解をrightAnswerにセット
        rightAnswer = quiz.get(1);

        // quiz配列から問題文を削除
        quiz.remove(0);

        // 正解と選択肢3つをシャッフル
        Collections.shuffle(quiz);

        // 回答ボタンに正解と選択肢3つを表示
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        // このクイズをauizArrayから削除
        quizArray.remove(randomNum);
    }

    public void checkAnswer(View view) {

        // どの回答ボタンが押されたか
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;
        if (btnText.equals(rightAnswer)) {
            alertTitle = "正解！";
            rightAnswerCount++;
        } else {
            alertTitle = "不正解...";
        }

        // ダイアログを作成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("答え：" + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // OKボタンが押された時の処理
                if (quizCount == QUIZ_COUNT) {
                    // 結果画面を表示
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    startActivity(intent);
                } else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}









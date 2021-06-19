package com.example.androidquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidquizapp.Model.History;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RankScore2 extends AppCompatActivity {

    RecyclerView rankingList;
    LinearLayoutManager layoutManager;
//    FirebaseRecyclerOptions<Ranking> options;
//    FirebaseRecyclerAdapter<Ranking, RankingViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference questionScore,rankingTbl;
    History history;
    int sum=0;
    String name;
    List<History> historyList = new ArrayList<>();
    //HashSet<RankTest> rankTestArrayListSorted = new HashSet<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_score2);

        database = FirebaseDatabase.getInstance();
        questionScore = database.getReference("Question_Score");
        rankingTbl = database.getReference("Ranking");

        // Init View
        rankingList = findViewById(R.id.rankingList);
        layoutManager = new LinearLayoutManager(this);
        rankingList.setHasFixedSize(true);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        rankingList.setLayoutManager(layoutManager);

        FirebaseDatabase.getInstance().getReference("History").addValueEventListener(valueEventListener);

        // Now we need implement callback
//        updateScore(Common.currentUser.getName(), new RankingCallback<Ranking>() {
//            @Override
//            public void callBack(Ranking ranking) {
//                // Update to ranking table
////                rankingTbl.child(ranking.getUserName())
////                        .setValue(ranking);
//                //showRanking(); // After upload, we need sort ranking table and show result
//                RankTest history=new RankTest();
//                history.setUserName(name);
//                history.setScore(sum);
//                historyList.add(history);
//
//
//                Toast.makeText(RankScore2.this,sum+"",Toast.LENGTH_SHORT).show();
//
//                if(!history.getUserName().isEmpty())
//                {
//                    HistoryAdapter rankTestAdapter = new HistoryAdapter(RankScore2.this, historyList);
//                    rankingList.setAdapter(rankTestAdapter);
//                    rankTestAdapter.notifyDataSetChanged();
//                }
//            }
//        });
//        // Set Options
//        options = new FirebaseRecyclerOptions.Builder<Ranking>()
//                .setQuery(database.getReference(),Ranking.class).build();
        // Set Adapter
//        adapter = new FirebaseRecyclerAdapter<Ranking, RankingViewHolder>(options) {
//            @NonNull
//            @Override
//            public RankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ranking,parent,false);
//                return new RankingViewHolder(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull RankingViewHolder viewHolder, int position, @NonNull Ranking model) {
//
//                viewHolder.txt_name.setText(model.getUserName());
//                viewHolder.txt_score.setText(String.valueOf(model.getScore()));
//            }
//
//        };
//
//        adapter.notifyDataSetChanged();
//        rankingList.setAdapter(adapter);


    }

//    private void showRanking() {
//        rankingTbl.orderByChild("score")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for (DataSnapshot data:dataSnapshot.getChildren())
//                        {
//                            Ranking local = data.getValue(Ranking.class);
//                            Log.d("DEBUG",local.getUserName());
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//    }


//     Here we need create interface Callback to process value
//    private void updateScore(final String userName, final RankingCallback<Ranking> callback) {
//        questionScore.orderByChild("user").equalTo(userName)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot data:dataSnapshot.getChildren())
//                        {
//                            QuestionScore ques = data.getValue(QuestionScore.class);
//                            sum+=Integer.parseInt(ques.getScore());
//                            name=userName;
//                        }
//                        // After sumary all score, we need process sum variable here
//                        // Because Firebase is async db, so if process outside, our 'sum'
//                        // value will be resetto 0
//                        Ranking ranking = new Ranking(userName,sum);
//                        callback.callBack(history);
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists())
            {
                historyList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    history = snapshot.getValue(History.class);
                    historyList.add(history);
                }

                if(historyList.size()!=0)
                {

                    //Toast.makeText(RankScore2.this,history.getUserName(),Toast.LENGTH_SHORT).show();
                    HistoryAdapter historyAdapter = new HistoryAdapter(RankScore2.this, historyList);
                    rankingList.setAdapter(historyAdapter);
                    historyAdapter.notifyDataSetChanged();
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goToMainActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToMainActivity() {
        Intent intentMainActivity = new Intent(getApplicationContext(), Home.class);
        startActivity(intentMainActivity);
    }
//
//    private ArrayList<RankTest> SortRankbyScore() {
//        rankTestArrayListSorted.clear();
//        Long [] scoreList = new Long[historyList.size()];
//        for(int i =0 ;i<historyList.size();i++)
//        {
//            scoreList[i]=historyList.get(i).getScore();
//        }
//
//        Arrays.sort(scoreList);
//
//        for(int i=0;i<historyList.size();i++)
//        {
//            for(int j=0;j<historyList.size();j++)
//            {
//                if(historyList.get(j).getScore() == scoreList[i])
//                {
//                    rankTestArrayListSorted.add(historyList.get(j));
//                }
//            }
//        }
//
//        return new ArrayList<>(rankTestArrayListSorted);
//    }

}

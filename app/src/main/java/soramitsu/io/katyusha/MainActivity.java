package soramitsu.io.katyusha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Navigator {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotoTop();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag(TopFragment.TAG) != null) {
            super.onBackPressed();
        } else {
            gotoTop();
        }
    }

    @Override
    public void gotoTop() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("HOME");
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, TopFragment.newInstance(), TopFragment.TAG)
                .commit();
    }

    @Override
    public void gotoTransaction() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Transaction");
        }
    }

    @Override
    public void gotoBadgeList() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Badge List");
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, BadgeFragment.newInstance(), BadgeFragment.TAG)
                .commit();
    }

    @Override
    public void gotoTransactionHistory() {
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle("Transaction History");
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, TransactionHistoryFragment.newInstance(), TransactionHistoryFragment.TAG)
                .commit();
    }
}

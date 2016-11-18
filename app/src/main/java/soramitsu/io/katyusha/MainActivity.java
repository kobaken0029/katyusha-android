package soramitsu.io.katyusha;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import soramitsu.io.katyusha.databinding.ActivityMainBinding;
import soramitsu.io.katyusha.entity.UserInfo;

public class MainActivity extends AppCompatActivity implements Navigator {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
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
        allClearMenuChecked();
        binding.navigationView.getMenu().getItem(0).setChecked(true);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, TopFragment.newInstance(), TopFragment.TAG)
                .commit();
    }

    @Override
    public void gotoTransaction() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Transaction");
        }
        allClearMenuChecked();
        binding.navigationView.getMenu().getItem(1).setChecked(true);
    }

    @Override
    public void gotoBadgeList() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Badge List");
        }
        allClearMenuChecked();
        binding.navigationView.getMenu().getItem(2).setChecked(true);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, BadgeFragment.newInstance(), BadgeFragment.TAG)
                .commit();
    }

    @Override
    public void gotoTransactionHistory() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Transaction History");
        }
        allClearMenuChecked();
        binding.navigationView.getMenu().getItem(3).setChecked(true);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, TransactionHistoryFragment.newInstance(), TransactionHistoryFragment.TAG)
                .commit();
    }

    private void init() {
        initToolbar();
        initNavigationHeader();
        initNavigationView();
        gotoTop();
    }

    private void initToolbar() {
        binding.toolbar.setNavigationIcon(getDrawable(R.drawable.ic_menu_white_24dp));
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        binding.toolbar.setNavigationOnClickListener(view -> binding.drawerLayout.openDrawer(GravityCompat.START));
    }

    private void initNavigationHeader() {
        UserInfo userInfo = ((Katyusha) getApplication()).getUserInfo();
        View headerView = binding.navigationView.getHeaderView(0);
        ((CircleImageView) headerView.findViewById(R.id.icon)).setImageDrawable(userInfo.image);
        ((TextView) headerView.findViewById(R.id.name)).setText(userInfo.alias);
        ((TextView) headerView.findViewById(R.id.email)).setText(userInfo.email);
    }

    private void initNavigationView() {
        binding.navigationView.setNavigationItemSelectedListener(item -> {
            boolean isChecked = item.isChecked();
            switch (item.getItemId()) {
                case R.id.menu_home:
                    if (isChecked) break;
                    gotoTop();
                    break;
                case R.id.menu_transaction:
                    if (isChecked) break;
                    gotoTransaction();
                    break;
                case R.id.menu_badge:
                    if (isChecked) break;
                    gotoBadgeList();
                    break;
                case R.id.menu_transaction_history:
                    if (isChecked) break;
                    gotoTransactionHistory();
                    break;
                case R.id.menu_setting:
                    Toast.makeText(getApplicationContext(), "Setting!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu_help:
                    Toast.makeText(getApplicationContext(), "Help!", Toast.LENGTH_SHORT).show();
                    break;
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void allClearMenuChecked() {
        for (int i = 0; i < 4; i++) {
            binding.navigationView.getMenu().getItem(i).setChecked(false);
        }
    }
}

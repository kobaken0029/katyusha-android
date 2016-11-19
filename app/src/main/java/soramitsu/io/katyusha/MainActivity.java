package soramitsu.io.katyusha;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
            if (getSupportFragmentManager().findFragmentByTag(ConfirmTransactionFragment.TAG) != null
                    || getSupportFragmentManager().findFragmentByTag(ReceiveFragment.TAG) != null) {
                gotoTransaction();
                return;
            }
            gotoTop();
        }
    }

    @Override
    public void gotoTop() {
        initToolbar();
        allClearMenuChecked();
        binding.navigationView.getMenu().getItem(0).setChecked(true);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, TopFragment.newInstance(), TopFragment.TAG)
                .commit();
    }

    @Override
    public void gotoTransaction() {
        changeBackingToolbar(getString(R.string.transaction));
        allClearMenuChecked();
        binding.navigationView.getMenu().getItem(1).setChecked(true);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, TransactionFragment.newInstance(), TransactionFragment.TAG)
                .commit();
    }

    @Override
    public void gotoConfirmTransaction() {
        changeToolbar(getString(R.string.confirm), R.drawable.ic_arrow_back_white_24dp, v -> gotoTransaction());
        allClearMenuChecked();
        binding.navigationView.getMenu().getItem(1).setChecked(true);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, ConfirmTransactionFragment.newInstance(), ConfirmTransactionFragment.TAG)
                .commit();
    }

    @Override
    public void gotoReceive() {
        changeToolbar(getString(R.string.receive), R.drawable.ic_arrow_back_white_24dp, v -> gotoTransaction());
        allClearMenuChecked();
        binding.navigationView.getMenu().getItem(1).setChecked(true);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, ReceiveFragment.newInstance(), ReceiveFragment.TAG)
                .commit();
    }

    @Override
    public void gotoBadgeList() {
        changeBackingToolbar(getString(R.string.badges));
        allClearMenuChecked();
        binding.navigationView.getMenu().getItem(2).setChecked(true);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, BadgeFragment.newInstance(), BadgeFragment.TAG)
                .commit();
    }

    @Override
    public void gotoTransactionHistory() {
        changeBackingToolbar(getString(R.string.transaction_history));
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
        changeToolbar(
                getString(R.string.home),
                R.drawable.ic_menu_white_24dp,
                view -> binding.drawerLayout.openDrawer(GravityCompat.START)
        );
    }

    private void changeBackingToolbar(@NonNull String title) {
        changeToolbar(title, R.drawable.ic_arrow_back_white_24dp, view -> gotoTop());
    }

    private void changeToolbar(@NonNull String title, @DrawableRes int navigationIcon, View.OnClickListener listener) {
        binding.toolbar.setTitle(title);
        binding.toolbar.setNavigationIcon(getDrawable(navigationIcon));
        binding.toolbar.setNavigationOnClickListener(listener);
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

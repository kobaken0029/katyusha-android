package soramitsu.io.katyusha.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import soramitsu.io.katyusha.Katyusha;
import soramitsu.io.katyusha.R;
import soramitsu.io.katyusha.databinding.ActivityMainBinding;
import soramitsu.io.katyusha.domain.entity.UserInfo;
import soramitsu.io.katyusha.view.fragment.BadgeFragment;
import soramitsu.io.katyusha.view.fragment.ConfirmTransactionFragment;
import soramitsu.io.katyusha.view.fragment.ReceiveFragment;
import soramitsu.io.katyusha.view.fragment.RightFragment;
import soramitsu.io.katyusha.view.fragment.RightsFragment;
import soramitsu.io.katyusha.view.fragment.TabHostFragment;
import soramitsu.io.katyusha.view.fragment.TopFragment;
import soramitsu.io.katyusha.view.fragment.TransactionFragment;

public class MainActivity extends AppCompatActivity implements Navigator {
    private ActivityMainBinding binding;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
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
            if (getSupportFragmentManager().findFragmentByTag(RightFragment.TAG) != null) {
                gotoRightsList();
                return;
            }
            gotoTop();
        }
    }

    @Override
    public void gotoTop() {
        initToolbar();
        allClearMenuChecked();
        binding.toolbar.setElevation(0);
        binding.navigationView.getMenu().getItem(0).setChecked(true);

        Explode explode = new Explode();
        TopFragment fragment = TopFragment.newInstance();
        fragment.setEnterTransition(explode);
        fragment.setExitTransition(explode);

        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, fragment, TopFragment.TAG)
                .commit();
    }

    @Override
    public void gotoTransaction() {
        initToolbar();
        binding.toolbar.setTitle(getString(R.string.transaction));
        allClearMenuChecked();
        binding.toolbar.setElevation(4);
        binding.navigationView.getMenu().getItem(1).setChecked(true);

        Slide open = new Slide();
        open.setSlideEdge(Gravity.END);
        Slide close = new Slide();
        close.setSlideEdge(Gravity.START);

        TransactionFragment fragment = TransactionFragment.newInstance();
        fragment.setEnterTransition(open);
        fragment.setExitTransition(new Explode());

        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, fragment, TransactionFragment.TAG)
                .commit();
    }

    @Override
    public void gotoConfirmTransaction(@NonNull String target, @NonNull String sender) {
        changeToolbar(getString(R.string.confirm), R.drawable.ic_arrow_back_white_24dp, v -> gotoTransaction());
        allClearMenuChecked();
        binding.toolbar.setElevation(4);
        binding.navigationView.getMenu().getItem(1).setChecked(true);
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, ConfirmTransactionFragment.newInstance(target, sender), ConfirmTransactionFragment.TAG)
                .commit();
    }

    @Override
    public void gotoReceive() {
        changeToolbar(getString(R.string.receive), R.drawable.ic_arrow_back_white_24dp, v -> gotoTransaction());
        allClearMenuChecked();
        binding.toolbar.setElevation(4);
        binding.navigationView.getMenu().getItem(1).setChecked(true);

        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.END);

        ReceiveFragment fragment = ReceiveFragment.newInstance();
        fragment.setEnterTransition(slide);
        fragment.setExitTransition(slide);

        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, fragment, ReceiveFragment.TAG)
                .commit();
    }

    @Override
    public void gotoRightsList() {
        initToolbar();
        binding.toolbar.setTitle(getString(R.string.rights));
        allClearMenuChecked();
        binding.toolbar.setElevation(4);
        binding.navigationView.getMenu().getItem(2).setChecked(true);

        Slide open = new Slide();
        open.setSlideEdge(Gravity.END);

        RightsFragment fragment = RightsFragment.newInstance();
        fragment.setEnterTransition(open);
        fragment.setExitTransition(new Explode());

        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, fragment, RightsFragment.TAG)
                .commit();
    }

    @Override
    public void gotoRight(@NonNull String target) {
        changeToolbar(target, R.drawable.ic_arrow_back_white_24dp, v -> gotoRightsList());
        allClearMenuChecked();
        binding.toolbar.setElevation(4);
        binding.navigationView.getMenu().getItem(2).setChecked(true);
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, RightFragment.newInstance(target), RightFragment.TAG)
                .commit();
    }

    @Override
    public void gotoBadgeList() {
        initToolbar();
        binding.toolbar.setTitle(getString(R.string.badges));
        allClearMenuChecked();
        binding.toolbar.setElevation(4);
        binding.navigationView.getMenu().getItem(3).setChecked(true);

        Slide open = new Slide();
        open.setSlideEdge(Gravity.END);

        BadgeFragment fragment = BadgeFragment.newInstance();
        fragment.setEnterTransition(open);
        fragment.setExitTransition(new Explode());

        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, fragment, BadgeFragment.TAG)
                .commit();
    }

    @Override
    public void gotoTabHost() {
        initToolbar();
        binding.toolbar.setTitle(getString(R.string.transaction_history));
        allClearMenuChecked();
        binding.toolbar.setElevation(0);
        binding.navigationView.getMenu().getItem(4).setChecked(true);
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, TabHostFragment.newInstance(), TabHostFragment.TAG)
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

    private void changeToolbar(@NonNull String title, @DrawableRes int navigationIcon,
                               View.OnClickListener listener) {
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
                case R.id.menu_rights:
                    if (isChecked) break;
                    gotoRightsList();
                    break;
                case R.id.menu_badge:
                    if (isChecked) break;
                    gotoBadgeList();
                    break;
                case R.id.menu_transaction_history:
                    if (isChecked) break;
                    gotoTabHost();
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
        for (int i = 0; i < 5; i++) {
            binding.navigationView.getMenu().getItem(i).setChecked(false);
        }
    }
}

package com.example.rvscroller;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RVScroller extends RecyclerView.OnScrollListener implements View.OnClickListener {

    private int delay;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private boolean endVisibility;

    public static final int NO_DELAY = -1;

    @SuppressLint("RestrictedApi")
    private RVScroller(Custom scrollerBuilder) {
        this.fab = scrollerBuilder.fab;
        this.delay = scrollerBuilder.delay;
        this.endVisibility = scrollerBuilder.endVisibility;

        fab.setOnClickListener(this);
        fab.setVisibility(View.GONE);

        checkForExceptions();
    }

    /**
     * Check for invalid parameters
     */
    private void checkForExceptions() {
        if (delay < -1) {
            throw new IllegalArgumentException("Invalid delay value");
        }
    }


    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        this.recyclerView = recyclerView;
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            // If cannot scroll up anymore (top of the recyclerview) - FAB hides immediately
            if (!recyclerView.canScrollVertically(-1) &&
                    fab.getVisibility() == View.VISIBLE) {
                fab.setEnabled(false);
                fab.hide();
            } else if (!recyclerView.canScrollVertically(1)) {
                // If cannot scroll down anymore (bottom of the recyclerview)
               /* fab.setEnabled(true);
                fab.show();*/
                setEndVisibility(endVisibility);
            } else {
                setDelay(delay);
            }
        }
    }


    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        // Detects if the recyclerview is scrolling both up or down
        if (dy > 0 && fab.getVisibility() == View.GONE || dy < 0 &&
                fab.getVisibility() == View.GONE) {
            fab.show();
            fab.setEnabled(true);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == fab.getId()) {
            recyclerView.smoothScrollToPosition(0);
        }
    }

    /**
     * Sets delay for the FAB disappearing when not at Top Or Bottom of RecyclerView.
     * If delay == NO_DELAY, this functionality is disabled -- FAB with always remain visible when not at top or bottom
     *
     * @param delay
     */
    private void setDelay(int delay) {
        if (delay != NO_DELAY) {
            Handler handler = new Handler();
            // If not at bottom or top, FAB will disappear after 500ms
            if (fab.getVisibility() == View.VISIBLE)
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fab.setEnabled(false);
                        fab.hide();
                    }
                }, delay);
        }
    }

    /**
     * Set visibility of FAB when RecyclerView is at the bottom
     *
     * @param endVisibility
     */
    private void setEndVisibility(boolean endVisibility) {
        if (endVisibility) {
            fab.setEnabled(true);
            fab.show();
        } else {
            Handler handler = new Handler();
            // If not at bottom or top, FAB will disappear after 500ms
            if (fab.getVisibility() == View.VISIBLE)
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fab.setEnabled(false);
                        fab.hide();
                    }
                }, 500);
        }
    }

    public static class Custom {
        private int delay;
        private boolean endVisibility;
        private FloatingActionButton fab;

        public Custom(@NonNull FloatingActionButton fab, @NonNull boolean endVisibility) {
            this.fab = fab;
            this.endVisibility = endVisibility;
        }

        public Custom withDelay(int delay) {
            this.delay = delay;
            return this;
        }

        public RVScroller build() {
            return new RVScroller(this);
        }
    }

}

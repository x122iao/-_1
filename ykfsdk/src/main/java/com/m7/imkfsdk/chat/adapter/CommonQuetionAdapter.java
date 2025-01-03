package com.m7.imkfsdk.chat.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.m7.imkfsdk.R;
import com.m7.imkfsdk.chat.CommonDetailQuestionActivity;
import com.m7.imkfsdk.chat.model.CommonQuestionBean;

import java.util.List;

/**
 * @FileName: LogisticsInfoRxListAdapter
 * @Description: 常见问题列表adapter
 * @Author:R-D
 * @CreatDate: 2019-12-03 10:31
 * @Reviser:
 * @Modification Time:2019-12-03 10:31
 */
public class CommonQuetionAdapter extends RecyclerView.Adapter<CommonQuetionAdapter.ViewHolder> {

    private final List<CommonQuestionBean> list;
    private final Context mContext;

    public CommonQuetionAdapter(Context context, List<CommonQuestionBean> list) {
        this.mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ykfsdk_item_question_parent, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv_commonQuetion.setText(list.get(i).getTabContent());

        viewHolder.rl_OneQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ToastUtils.showShort("点击了" + list.get(i).getTabContent());
                Intent intent = new Intent(mContext, CommonDetailQuestionActivity.class);
                intent.putExtra("tabId", list.get(i).getTabId());
                mContext.startActivity(intent);
            }
        });
        if (i == list.size() - 1) {
            viewHolder.view_line.setVisibility(View.GONE);
        } else {
            viewHolder.view_line.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView tv_commonQuetion;
        public final RelativeLayout rl_OneQuestion;
        public final View view_line;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_commonQuetion = itemView.findViewById(R.id.tv_commonQuetion);
            rl_OneQuestion = itemView.findViewById(R.id.rl_OneQuestion);
            view_line = itemView.findViewById(R.id.view_line);
        }

    }

}

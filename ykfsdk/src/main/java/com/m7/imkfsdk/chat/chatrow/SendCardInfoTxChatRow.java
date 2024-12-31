package com.m7.imkfsdk.chat.chatrow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.m7.imkfsdk.MoorWebCenter;
import com.m7.imkfsdk.R;
import com.m7.imkfsdk.chat.ChatActivity;
import com.m7.imkfsdk.chat.holder.BaseHolder;
import com.m7.imkfsdk.chat.holder.SendCardInfoTxHolder;
import com.m7.imkfsdk.chat.holder.ViewHolderTag;
import com.m7.imkfsdk.utils.DensityUtil;
import com.moor.imkf.IMChatManager;
import com.moor.imkf.lib.utils.MoorLogUtils;
import com.moor.imkf.model.entity.FromToMessage;
import com.moor.imkf.model.entity.NewCardInfo;
import com.moor.imkf.model.entity.NewCardInfoTags;
import com.moor.imkf.utils.MoorUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Description:点击发给坐席的卡片类型
 *
 * @Author R-D
 * @Modification Time:2019/12/20
 */
public class SendCardInfoTxChatRow extends BaseChatRow {

    public SendCardInfoTxChatRow(int type) {
        super(type);
    }

    @Override
    public boolean onCreateRowContextMenu(ContextMenu contextMenu, View targetView, FromToMessage detail) {
        return false;
    }

    @Override
    protected void buildChattingData(final Context context, BaseHolder baseHolder, final FromToMessage message, int position) {
        SendCardInfoTxHolder holder = (SendCardInfoTxHolder) baseHolder;
        if (message != null && message.newCardInfo != null) {
            Type token = new TypeToken<NewCardInfo>() {
            }.getType();

            NewCardInfo newCardInfo = new Gson().fromJson(message.newCardInfo, token);

            //移除不需要访客端展示的数据
            newCardInfo = MoorUtils.removeBtnTag(newCardInfo);

            holder.tv_child_title.setText(newCardInfo.getTitle());
            holder.tv_child_.setText(newCardInfo.getSub_title());

            String img = newCardInfo.getImg();

            if (IMChatManager.getInstance().getImageLoader() != null) {
                IMChatManager.getInstance().getImageLoader().loadImage(false, false, img,
                        holder.iv_child_img, 0, 0, 8, null, null, null);
            } else {
                MoorLogUtils.eTag("ImageLoader", "ImageLoader is null");
            }

            View.OnClickListener listener = ((ChatActivity) context).getChatAdapter().getOnClickListener();
            ViewHolderTag clickTag = ViewHolderTag.createTag(newCardInfo.getTarget(), ViewHolderTag.TagType.TAG_CLICK_NEW_CARD);
            holder.kf_chat_rich_lin.setTag(clickTag);
            holder.kf_chat_rich_lin.setOnClickListener(listener);
            getMsgStateResId(position, holder, message, listener);


            if (newCardInfo.getAttr_one() != null) {
                holder.tv_logistics_tx_num.setText(newCardInfo.getAttr_one().getContent());

                String color = newCardInfo.getAttr_one().getColor();
                if (color.contains("#")) {
                    try {
                        holder.tv_logistics_tx_num.setTextColor(Color.parseColor(color));
                    } catch (Exception e) {

                    }
                }
            }
            if (newCardInfo.getAttr_two() != null) {
                holder.tv_logistics_tx_state.setText(newCardInfo.getAttr_two().getContent());
                String color = newCardInfo.getAttr_two().getColor();
                if (color.contains("#")) {
                    try {
                        holder.tv_logistics_tx_state.setTextColor(Color.parseColor(color));
                    } catch (Exception e) {

                    }
                }
            }
            if (!"".equals(newCardInfo.getPrice())) {
                holder.tv_logistics_tx_price.setVisibility(View.VISIBLE);
                holder.tv_logistics_tx_state.setVisibility(View.VISIBLE);
                holder.tv_logistics_tx_num.setVisibility(View.VISIBLE);
                holder.tv_logistics_tx_price.setText(newCardInfo.getPrice());
            } else {
                holder.tv_logistics_tx_price.setVisibility(View.GONE);
                holder.tv_logistics_tx_state.setVisibility(View.GONE);
                holder.tv_logistics_tx_num.setVisibility(View.GONE);
            }
            List<String> list = new ArrayList<>();

            if (!"".equals(newCardInfo.getOther_title_one())) {
                list.add(newCardInfo.getOther_title_one());
            }
            if (!"".equals(newCardInfo.getOther_title_two())) {
                list.add(newCardInfo.getOther_title_two());
            }
            if (!"".equals(newCardInfo.getOther_title_three())) {
                list.add(newCardInfo.getOther_title_three());
            }
            if (list.size() > 0) {
                holder.ll_other_title.removeAllViews();
                holder.ll_other_title.setVisibility(View.VISIBLE);
                for (String s : list) {
                    TextView textView = new TextView(context);
                    textView.setPadding(DensityUtil.dp2px(8), 0, DensityUtil.dp2px(8), DensityUtil.dp2px(8) / 2);
                    textView.setTextSize(12f);
                    textView.setMaxLines(1);
                    textView.setEllipsize(TextUtils.TruncateAt.END);
                    textView.setTextColor(context.getResources().getColor(R.color.ykfsdk_color_666666));
                    textView.setText(s);
                    holder.ll_other_title.addView(textView);
                }

            } else {
                holder.ll_other_title.setVisibility(View.GONE);
            }

            if (newCardInfo.getTags() != null) {
                if (newCardInfo.getTags().size() > 0) {
                    holder.line.setVisibility(View.VISIBLE);
                    if (newCardInfo.getTags().size() >= 2) {
                        //  这个是上下的布局
                        if (newCardInfo.getTags().get(0).getLabel().length() > 4 || newCardInfo.getTags().get(1).getLabel().length() > 4) {
                            addVerticalView(holder, context, newCardInfo);
                        } else {
                            addhorizontalView(holder, context, newCardInfo);
                        }
                    } else if (newCardInfo.getTags().size() == 1) {
                        if (newCardInfo.getTags().get(0).getLabel().length() > 4) {
                            addVerticalView(holder, context, newCardInfo);
                        } else {
                            addhorizontalView(holder, context, newCardInfo);
                        }
                    }
                }
            }
        }
    }

    public void addVerticalView(SendCardInfoTxHolder holder, final Context context, final NewCardInfo newCardInfo) {
        Object oldTag = holder.kf_chat_rich_lin.getChildAt(holder.kf_chat_rich_lin.getChildCount() - 1).getTag();
        if (oldTag != null) {
            if (oldTag.equals("cardButtonVertical")) {
                return;
            } else if (oldTag.equals("cardButtonHorizontal")) {
                holder.kf_chat_rich_lin.removeView(holder.kf_chat_rich_lin.getChildAt(holder.kf_chat_rich_lin.getChildCount() - 1));
            }
        }
        View view = LayoutInflater.from(context).inflate(R.layout.ykfsdk_layout_card_button_vertical, holder.kf_chat_rich_lin, false);
        view.setTag("cardButtonVertical");
        TextView topBt = view.findViewById(R.id.top_button);
        TextView buttomBt = view.findViewById(R.id.buttom_button);

        if (newCardInfo.getTags().size() >= 2) {
            topBt.setText(newCardInfo.getTags().get(0).getLabel());
            buttomBt.setText(newCardInfo.getTags().get(1).getLabel());
            if (newCardInfo.getTags().get(0).getUrl() != null) {
                topBt.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent forumIntent = new Intent(context, MoorWebCenter.class);
                        forumIntent.putExtra("OpenUrl", newCardInfo.getTags().get(0).getUrl());
                        context.startActivity(forumIntent);
                    }
                }));
            }
            if (newCardInfo.getTags().get(1).getUrl() != null) {
                buttomBt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent forumIntent = new Intent(context, MoorWebCenter.class);
                        forumIntent.putExtra("OpenUrl", newCardInfo.getTags().get(1).getUrl());
                        context.startActivity(forumIntent);
                    }
                });
            }
        } else if (newCardInfo.getTags().size() == 1) {
            buttomBt.setVisibility(View.GONE);
            if (newCardInfo.getTags().get(0).getUrl() != null) {
                topBt.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent forumIntent = new Intent(context, MoorWebCenter.class);
                        forumIntent.putExtra("OpenUrl", newCardInfo.getTags().get(0).getUrl());
                        context.startActivity(forumIntent);
                    }
                }));
            }
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.bottomMargin = DensityUtil.dp2px(15);
        holder.kf_chat_rich_lin.addView(view, layoutParams);
    }

    public void addhorizontalView(SendCardInfoTxHolder holder, final Context context, final NewCardInfo newCardInfo) {
        Object oldTag = holder.kf_chat_rich_lin.getChildAt(holder.kf_chat_rich_lin.getChildCount() - 1).getTag();
        if (oldTag != null) {
            if (oldTag.equals("cardButtonHorizontal")) {
                return;
            } else if (oldTag.equals("cardButtonVertical")) {
                holder.kf_chat_rich_lin.removeView(holder.kf_chat_rich_lin.getChildAt(holder.kf_chat_rich_lin.getChildCount() - 1));
            }
        }
        View view = LayoutInflater.from(context).inflate(R.layout.ykfsdk_layout_card_button_horizontal, holder.kf_chat_rich_lin, false);
        view.setTag("cardButtonHorizontal");
        TextView leftBt = view.findViewById(R.id.left_button);
        TextView rightBt = view.findViewById(R.id.right_buton);
        if (newCardInfo.getTags().size() >= 2) {
            leftBt.setText(newCardInfo.getTags().get(0).getLabel());
            rightBt.setText(newCardInfo.getTags().get(1).getLabel());
            leftBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (newCardInfo.getTags().get(0).getUrl() != null) {
                        Intent forumIntent = new Intent(context, MoorWebCenter.class);
                        forumIntent.putExtra("OpenUrl", newCardInfo.getTags().get(0).getUrl());
                        context.startActivity(forumIntent);
                    }
                }
            });
            rightBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (newCardInfo.getTags().get(1).getUrl() != null) {
                        Intent forumIntent = new Intent(context, MoorWebCenter.class);
                        forumIntent.putExtra("OpenUrl", newCardInfo.getTags().get(1).getUrl());
                        context.startActivity(forumIntent);
                    }
                }
            });
        } else if (newCardInfo.getTags().size() == 1) {
            leftBt.setVisibility(View.INVISIBLE);
            rightBt.setText(newCardInfo.getTags().get(0).getLabel());
            rightBt.setBackground(context.getResources().getDrawable(R.drawable.ykfsdk_bg_card_button_bule));
            rightBt.setTextColor(Color.WHITE);
            rightBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (newCardInfo.getTags().get(0).getUrl() != null) {
                        Intent forumIntent = new Intent(context, MoorWebCenter.class);
                        forumIntent.putExtra("OpenUrl", newCardInfo.getTags().get(0).getUrl());
                        context.startActivity(forumIntent);
                    }
                }
            });
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.bottomMargin = DensityUtil.dp2px(15);
        holder.kf_chat_rich_lin.addView(view, layoutParams);
    }

    @Override
    public View buildChatView(LayoutInflater inflater, View convertView) {
        //if (convertView == null) {
        convertView = inflater.inflate(R.layout.ykfsdk_kf_chat_row_newcard_info_tx, null);
        SendCardInfoTxHolder holder = new SendCardInfoTxHolder(mRowType);
        convertView.setTag(holder.initBaseHolder(convertView, false));
        //    }
        return convertView;
    }

    @Override
    public int getChatViewType() {
        return ChatRowType.SEND_ORDER_INFO_ROW_TRANSMIT.ordinal();
    }


}


/*==============================================================*/
/* DBMS name:      MySQL 5.7                                    */
/* Created on:     2018/2/27 11:43:14							*/
/* Created by:     huangyong.nsu@qq.com                         */
/*==============================================================*/


drop index idx_mifc_activity on activity;

drop table if exists activity;

drop index idx_mifc_address on address;

drop table if exists address;

drop index idx_mifc_bankcard on bankcard;

drop table if exists bankcard;

drop index idx_mifc_collector on collector;

drop table if exists collector;

drop index idx_mifc_comment on comment;

drop table if exists comment;

drop index idx_mifc_goods on goods;

drop table if exists goods;

drop index idx_mifc_invoice on invoice;

drop table if exists invoice;

drop index idx_mifc_logistics on logistics;

drop table if exists logistics;

drop index idx_mifc_member on member;

drop table if exists member;

drop index idx_mifc_order on orders;

drop table if exists orders;

drop index idx_mifc_pay on pay;

drop table if exists pay;

drop index idx_mifc_seller on seller;

drop table if exists seller;

drop index idx_mifc_trolley on trolley;

drop table if exists trolley;

/*==============================================================*/
/* Table: activity                                              */
/*==============================================================*/
create table activity
(
   id          			bigint not null auto_increment comment 'id',
   activity_no          varchar(16) not null comment '活动编号',
   activity_name        varchar(50) comment '活动名称',
   begin_time           datetime comment '开始时间',
   end_time             datetime comment '结束时间',
   activity_content     varchar(200) not null comment '活动内容',
   activity_type        int default 0 comment '活动类型（0：无 1：折扣 2：限时优惠）',
   goods_no             varchar(16) comment '商品编号',
   seller_no            varchar(16) comment '卖家编号',
   status               int default 0 comment '状态（0：下线 1：上线）',
   activity_price       varchar(10) comment '活动价格',
   activity_discount    varchar(4) comment '活动折扣',
   primary key (id)
);

alter table activity comment '活动表';

/*==============================================================*/
/* Index: idx_mifc_activity                                     */
/*==============================================================*/
create index idx_mifc_activity on activity
(
   activity_no,
   seller_no
);

/*==============================================================*/
/* Table: address                                               */
/*==============================================================*/
create table address
(
   id           		bigint not null auto_increment comment 'id',
   member_no            varchar(16) not null comment '会员编号',
   province             varchar(20) not null comment '省',
   city                 varchar(20) not null comment '市',
   district             varchar(20) not null comment '区',
   street               varchar(20) comment '街道',
   address_detailed     varchar(50) comment '详细地址',
   create_time          datetime  comment '创建时间',
   status               int default 0 comment '状态',
   primary key (id)
);

alter table address comment '收货地址';

/*==============================================================*/
/* Index: idx_mifc_address                                      */
/*==============================================================*/
create index idx_mifc_address on address
(
   member_no
);

/*==============================================================*/
/* Table: bankcard                                              */
/*==============================================================*/
create table bankcard
(
   id          			bigint not null auto_increment comment 'id',
   card_no              varchar(16) not null comment '卡号',
   member_no            varchar(16) comment '会员编号',
   card_operator        varchar(20) not null comment '卡运营商',
   phone_no             varchar(13) not null comment '绑定手机号',
   true_name            varchar(10) not null comment '真实姓名',
   card_type            int not null comment '卡类型',
   primary key (id)
);

alter table bankcard comment '银行卡';

/*==============================================================*/
/* Index: idx_mifc_bankcard                                     */
/*==============================================================*/
create unique index idx_mifc_bankcard on bankcard
(
   card_no
);

/*==============================================================*/
/* Table: collector                                             */
/*==============================================================*/
create table collector
(
   id         			bigint not null auto_increment comment 'id',
   status               int default 0 comment '状态',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   goods_no             varchar(16) not null comment '商品编号',
   member_no            varchar(16) not null comment '会员编号',
   primary key (id)
);

alter table collector comment '收藏';

/*==============================================================*/
/* Index: idx_mifc_collector                                    */
/*==============================================================*/
create index idx_mifc_collector on collector
(
   member_no
);

/*==============================================================*/
/* Table: comment                                               */
/*==============================================================*/
create table comment
(
   id           		bigint not null auto_increment comment 'id',
   content              varchar(200) not null comment '内容',
   content_level        int default 5 comment '星级（1-5级）',
   content_time         datetime not null comment '评论时间',
   status               int default 0 comment '状态',
   goods_no             varchar(16) not null comment '商品编号',
   member_no            varchar(16) not null comment '会员编号',
   primary key (id)
);

alter table comment comment '评论';

/*==============================================================*/
/* Index: idx_mifc_comment                                      */
/*==============================================================*/
create index idx_mifc_comment on comment
(
   goods_no,
   member_no
);

/*==============================================================*/
/* Table: goods                                                 */
/*==============================================================*/
create table goods
(
   id             		bigint not null auto_increment comment 'id',
   goods_no             varchar(16) not null comment '商品编号',
   goods_name           varchar(20) not null comment '商品名称',
   seller_no            varchar(16) not null comment '卖家编号',
   title                varchar(64) not null comment '标题',
   goods_price          varchar(10) not null comment '价格',
   brand                varchar(30) comment '品牌',
   pack                 varchar(4) comment '包装',
   product_city         varchar(20) comment '原产地',
   category             varchar(10) comment '类别',
   diameter             varchar(9) comment '果实直径',
   activity_no          varchar(16) comment '活动编号',
   introduce            varchar(200) not null comment '介绍图片地址',
   status               int default 0 comment '状态（0：初始 1：上架 2：下架）',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   pic1_url             varchar(200) not null comment '图片1',
   pic2_url             varchar(200) comment '图片2',
   pic3_url             varchar(200) comment '图片3',
   pic4_url             varchar(200) comment '图片4',
   pic5_url             varchar(200) comment '图片5',
   primary key (id)
);

alter table goods comment '商品';

/*==============================================================*/
/* Index: idx_mifc_goods                                        */
/*==============================================================*/
create index idx_mifc_goods on goods
(
   goods_no,
   seller_no
);

/*==============================================================*/
/* Table: invoice                                               */
/*==============================================================*/
create table invoice
(
   id              		bigint not null auto_increment comment 'id',
   invoice_no           varchar(16) not null comment '发货单编号',
   goods_no             varchar(16) not null comment '商品编号',
   order_no             varchar(16) not null comment '订单编号',
   member_no            varchar(16) comment '会员编号',
   amount               int not null comment '数量',
   title                varchar(64) not null comment '标题',
   seller_no            varchar(16) not null comment '卖家编号',
   status               int default 0 comment '状态（0：待发货 1：已发货 2：已退单 3：已完成）',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(200) comment '备注',
   send_address         varchar(100) comment '发货地址',
   receive_address      varchar(100) not null comment '收货地址',
   receive_name         varchar(16) not null comment '收货人姓名',
   phone                varchar(11) not null comment '收货人电话',
   primary key (id)
);

alter table invoice comment '发货单';

/*==============================================================*/
/* Index: idx_mifc_invoice                                      */
/*==============================================================*/
create index idx_mifc_invoice on invoice
(
   invoice_no,
   seller_no
);

/*==============================================================*/
/* Table: logistics                                             */
/*==============================================================*/
create table logistics
(
   id         			bigint not null auto_increment comment 'id',
   logistics_no         varchar(16) not null comment '物流编号',
   send_address         varchar(100) not null comment '发送地址',
   receive_address      varchar(100) not null comment '接收地址',
   now_address          varchar(100) not null comment '当前地址',
   member_no            varchar(16) not null comment '会员编号',
   seller_no            varchar(16) not null comment '卖家编号',
   status               int default 0 comment '状态（0：发货中 1：派送中 2：待签收 3：已签收）',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   order_no           	varchar(16) comment '订单号',
   receive_name         varchar(16) not null comment '收货人姓名',
   phone                varchar(11) not null comment '收货人电话',
   primary key (id)
);

alter table logistics comment '物流';

/*==============================================================*/
/* Index: idx_mifc_logistics                                    */
/*==============================================================*/
create index idx_mifc_logistics on logistics
(
   logistics_no
);

/*==============================================================*/
/* Table: member                                                */
/*==============================================================*/
create table member
(
   id                   bigint not null auto_increment comment 'id',
   member_no            varchar(16) not null comment '会员编号',
   nickname             varchar(16) not null comment '昵称',
   sex                  varchar(2) not null comment '性别',
   true_name            varchar(10) comment '真实姓名',
   certificate_type     int comment '证件类型',
   certificate_no       varchar(18) comment '证件号',
   login_password       varchar(16) not null comment '登录密码',
   pay_password         numeric(6,0) comment '支付密码',
   phone_no             varchar(13) comment '电话号码',
   birth_date           date comment '出生日期',
   member_level         int default 0 comment '会员等级（0：普通会员 ）',
   due_time             datetime comment '会员到期时间',
   register_time        datetime not null comment '注册时间',
   status               int not null comment '状态（0：正常 1：锁定 2：停用 100：未实名）',
   head_portrait        varchar(200) not null comment '头像图片地址',
   primary key (id)
);

alter table member comment '会员';

/*==============================================================*/
/* Index: idx_mifc_member                                       */
/*==============================================================*/
create unique index idx_mifc_member on member
(
   member_no,
   certificate_no,
   phone_no
);

/*==============================================================*/
/* Table: orders                                               */
/*==============================================================*/
create table orders
(
   id             		bigint not null auto_increment comment 'id',
   order_no             varchar(16) not null comment '订单编号',
   create_time          datetime comment '创建时间',
   pay_time             datetime comment '支付时间',
   member_no            varchar(16) not null comment '会员编号',
   distribution_way     int not null comment '配送方式',
   invoice_type         int not null comment '发票类型',
   invoice_title        varchar(64) not null comment '发票抬头',
   invoice_content      varchar(200) comment '发票内容',
   goods_no             varchar(16) not null comment '商品编号',
   goods_number         varchar(10) not null comment '商品数量',
   freight              varchar(10) not null comment '运费',
   total_num            varchar(10) not null comment '需付款金额',
   receiv_address       varchar(200) not null comment '收货地址',
   remark               varchar(200) comment '备注',
   channel              numeric(4,0) default 0000 comment '渠道',
   status               int default 0 comment '状态（0：待付费 1：已付费 2：待完成 3：已完成）',
   primary key (id)
);

alter table orders comment '订单';

/*==============================================================*/
/* Index: idx_mifc_orders                                        */
/*==============================================================*/
create index idx_mifc_order on orders
(
   order_no,
   member_no
);

/*==============================================================*/
/* Table: pay                                                   */
/*==============================================================*/
create table pay
(
   id               	bigint not null auto_increment comment 'id',
   pay_no               varchar(11) not null comment '支付编号',
   order_no             varchar(16) not null comment '订单编号',
   member_no            varchar(16) not null comment '会员编号',
   pay_amount           varchar(10) not null comment '支付金额',
   pay_type             int not null comment '支付类型',
   pay_time             datetime not null comment '支付时间',
   status               int default 100 comment '状态（0:成功 1：失败）',
   primary key (id)
);

alter table pay comment '支付信息';

/*==============================================================*/
/* Index: idx_mifc_pay                                          */
/*==============================================================*/
create unique index idx_mifc_pay on pay
(
   pay_no
);

/*==============================================================*/
/* Table: seller                                                */
/*==============================================================*/
create table seller
(
   id            		bigint not null auto_increment comment 'id',
   seller_no            varchar(16) not null comment '卖家编号',
   nickname   			varchar(11) not null comment '昵称',
   sex                  varchar(2) not null comment '性别',
   true_name            varchar(10) not null comment '真实姓名',
   certificate_type     int not null comment '证件类型',
   certificate_no       varchar(18) not null comment '证件号',
   login_password       varchar(16) not null comment '登录密码',
   withdrawal_password  int comment '提现密码',
   phone_no             varchar(13) not null comment '手机号码',
   register_time        datetime not null comment '注册时间',
   status               int default 0 comment '状态（0：待审核 1：已实名 2：锁定 3：停用）',
   balance              varchar(10) not null comment '余额',
   head_portrait        varchar(200) not null comment '头像图片地址',
   send_address         varchar(100) comment '发货地址',
   primary key (id)
);

alter table seller comment '卖家';

/*==============================================================*/
/* Index: idx_mifc_seller                                       */
/*==============================================================*/
create index idx_mifc_seller on seller
(
   seller_no
);

/*==============================================================*/
/* Table: trolley                                               */
/*==============================================================*/
create table trolley
(
   id           		bigint not null auto_increment comment 'id',
   status               int default 100 comment '状态（0：代购 1：已购）',
   create_time          datetime not null comment '创建时间',
   goods_no             varchar(16) not null comment '商品编号',
   member_no            varchar(16) not null comment '会员编号',
   primary key (id)
);

alter table trolley comment '购物车';

/*==============================================================*/
/* Index: idx_mifc_trolley                                      */
/*==============================================================*/
create index idx_mifc_trolley on trolley
(
   member_no
);


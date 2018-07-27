/**
 * 商城管理--订单管理的单例对象
 */
var Orders = {
    id: "ordersTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    deptid:0
};

/**
 * 初始化表格的列
 */
Orders.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '用户名称', field: 'userName', align: 'center', valign: 'middle', sortable: true},
        {title: '订单号', field: 'code', align: 'center', valign: 'middle', sortable: true},
        {title: '订单标题', field: 'orderTitle', align: 'center', valign: 'middle', sortable: true},
        {title: '订单总金额（元）', field: 'totalCost', align: 'center', valign: 'middle', sortable: true},
        {title: '订单总数量', field: 'totalVolume', align: 'center', valign: 'middle', sortable: true},
        {title: '订单状态', field: 'orderStatus', align: 'center', valign: 'middle',formatter:statusFormatter, sortable: true},
        {title: '订单备注', field: 'orderRemark', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true}];
    return columns;
};

//订单状态字段格式化
//订单状态：0待付款 1待发货 2待收货 3待评价 4退款中 5已完成 6已取消 7已退款 8拒绝退款
function statusFormatter(value) {
    if (value == 0) { 
    	value = '待付款'; 
    }else if (value == 1) { 
    	value = '待发货'; 
    }else if (value == 2) { 
    	value = '待收货'; 
    }else if (value == 3) { 
    	value = '待评价'; 
    }else if (value == 4) { 
    	value = '退款中'; 
    }else if (value == 5) { 
    	value = '已完成'; 
    }else if (value == 6) { 
    	value = '已取消'; 
    }else if (value == 7) { 
    	value = '已退款'; 
    }else if (value == 8) { 
    	value = '拒绝退款'; 
    }else { 
    	value = '未知'; 
    }
    return value;
};

/**
 * 检查是否选中
 */
Orders.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Orders.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加管理员
 */
Orders.openAddMgr = function () {
    var index = layer.open({
        type: 2,
        title: '添加管理员',
        area: ['800px', '560px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/mgr/add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改按钮时
 * @param userId 管理员id
 */
Orders.openChangeUser = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑管理员',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mgr/edit?id=' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 点击查看按钮时
 */
Orders.openView = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '查看订单',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/orders/view?id=' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 点击角色分配
 * @param
 */
Orders.roleAssign = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '角色分配',
            area: ['300px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mgr/assignRole?userId=' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户
 */
Orders.delOrders = function () {
    if (this.check()) {
    	
    	var operation = function(){
            var userId = Orders.seItem.id;
			var ids = new Array();
			ids[0] = userId;
			var paramsData = {};
            paramsData['ids'] = ids;
            var ajax = new $ax(Feng.ctxPath + "/mgr/delete", function (data) {
            	if(data.code == 1){
            		Feng.success("删除成功!");
                    Orders.table.refresh();
            	}else{
            		Feng.error("删除失败!"+data.message);
            	}
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set(paramsData);
            ajax.setBeanData();
            ajax.start();
        };

        Feng.confirm("是否删除用户" + Orders.seItem.account + "?",operation);
    }
};

/**
 * 冻结用户账户
 * @param userId
 */
Orders.freezeAccount = function () {
    if (this.check()) {
        var userId = this.seItem.id;
        var ajax = new $ax(Feng.ctxPath + "/mgr/freeze", function (data) {
            Feng.success("冻结成功!");
            Orders.table.refresh();
        }, function (data) {
            Feng.error("冻结失败!" + data.responseJSON.message + "!");
        });
        ajax.set("userId", userId);
        ajax.start();
    }
};

/**
 * 解除冻结用户账户
 * @param userId
 */
Orders.unfreeze = function () {
    if (this.check()) {
        var userId = this.seItem.id;
        var ajax = new $ax(Feng.ctxPath + "/mgr/unfreeze", function (data) {
            Feng.success("解除冻结成功!");
            Orders.table.refresh();
        }, function (data) {
            Feng.error("解除冻结失败!");
        });
        ajax.set("userId", userId);
        ajax.start();
    }
}

/**
 * 重置密码
 */
Orders.resetPwd = function () {
    if (this.check()) {
        var userId = this.seItem.id;
        parent.layer.confirm('是否重置密码为123456？', {
            btn: ['确定', '取消'],
            shade: false //不显示遮罩
        }, function () {
        	var paramsData = {};
        	paramsData['id'] = userId;
            var ajax = new $ax(Feng.ctxPath + "/mgr/reset", function (data) {
            	if(data.code == 1){
            		Feng.success("重置密码成功!");
            	}else{
            		Feng.error("重置密码失败!"+data.message);
            	}
            }, function (data) {
                Feng.error("重置密码失败!" + data.responseJSON.message + "!");
            });
            ajax.set(paramsData);
            ajax.setBeanData();
            ajax.start();
        });
    }
};

Orders.resetSearch = function () {
    $("#searchKey").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    Orders.search();
}

Orders.search = function () {
    var queryData = {};

    //queryData['deptid'] = Orders.deptid;
    queryData['searchKey'] = $("#searchKey").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();

    Orders.table.refresh({query: queryData});
}

Orders.onClickDept = function (e, treeId, treeNode) {
    Orders.deptid = treeNode.id;
    Orders.search();
};

$(function () {
    var defaultColunms = Orders.initColumn();
    var table = new BSTable("ordersTable", "/orders/listByPage", defaultColunms);
    table.setMethod("get");
    table.setPaginationType("server");
    Orders.table = table.init();
});

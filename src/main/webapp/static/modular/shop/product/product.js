/**
 * 商城管理--商品管理的单例对象
 */
var Product = {
    id: "productTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    deptid:0
};

/**
 * 初始化表格的列
 */
Product.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '编号', field: 'code', align: 'center', valign: 'middle', sortable: true},
        {title: '封面', field: 'coverPic', align: 'center', valign: 'middle', sortable: true},
        {title: '标题', field: 'productTitle', align: 'center', valign: 'middle', sortable: true},
        {title: '商品简介', field: 'productBrief', align: 'center', valign: 'middle', sortable: true},
        {title: '详情介绍', field: 'productDesc', align: 'center', valign: 'middle', sortable: true},
        {title: '原价（元）', field: 'originalPrice', align: 'center', valign: 'middle', sortable: true},
        {title: '售价（元）', field: 'salePrice', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true}];
    return columns;
};

/**
 * 检查是否选中
 */
Product.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Product.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加商品
 */
Product.openAddProduct = function () {
    var index = layer.open({
        type: 2,
        title: '添加商品',
        area: ['800px', '560px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/product/add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改按钮时
 */
Product.openChangeProduct = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑商品',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/product/edit?id=' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 点击角色分配
 * @param
 */
Product.roleAssign = function () {
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
Product.delProduct = function () {
    if (this.check()) {

    	var operation = function(){
            var productId = Product.seItem.id;
			var ids = new Array();
			ids[0] = productId;
			var paramsData = {};
            paramsData['ids'] = ids;
            var ajax = new $ax(Feng.ctxPath + "/product/delete", function (data) {
            	if(data.code == 1){
            		Feng.success("删除成功!");
                    Product.table.refresh();
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

        Feng.confirm("是否删除?",operation);
    }
};

/**
 * 冻结用户账户
 * @param userId
 */
Product.freezeAccount = function () {
    if (this.check()) {
        var userId = this.seItem.id;
        var ajax = new $ax(Feng.ctxPath + "/mgr/freeze", function (data) {
            Feng.success("冻结成功!");
            Product.table.refresh();
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
Product.unfreeze = function () {
    if (this.check()) {
        var userId = this.seItem.id;
        var ajax = new $ax(Feng.ctxPath + "/mgr/unfreeze", function (data) {
            Feng.success("解除冻结成功!");
            Product.table.refresh();
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
Product.resetPwd = function () {
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

Product.resetSearch = function () {
    $("#searchKey").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    Product.search();
}

Product.search = function () {
    var queryData = {};

    //queryData['deptid'] = Product.deptid;
    queryData['searchKey'] = $("#searchKey").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();

    Product.table.refresh({query: queryData});
}

Product.onClickDept = function (e, treeId, treeNode) {
    Product.deptid = treeNode.id;
    Product.search();
};

$(function () {
    var defaultColunms = Product.initColumn();
    var table = new BSTable(Product.id, "/product/listByPage", defaultColunms);
    table.setMethod("get");
    table.setPaginationType("server");
    Product.table = table.init();
});

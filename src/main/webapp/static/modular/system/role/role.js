/**
 * 角色管理的单例
 */
var Role = {
    id: "roleTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Role.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '上级角色', field: 'pName', align: 'center', valign: 'middle', sortable: true},
        //{title: '所在部门', field: 'deptName', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true},
        {title: '别名', field: 'tips', align: 'center', valign: 'middle', sortable: true}]
    return columns;
};


/**
 * 检查是否选中
 */
Role.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Role.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加管理员
 */
Role.openAddRole = function () {
    var index = layer.open({
        type: 2,
        title: '添加角色',
        area: ['800px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/role/add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改按钮时
 */
Role.openChangeRole = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改角色',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/role/edit?id=' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除角色
 */
Role.delRole = function () {
    if (this.check()) {

        var operation = function(){
        	var roleId = Role.seItem.id;
        	var ids = new Array();
			ids[0] = roleId;
			var paramsData = {};
            paramsData['ids'] = ids;
            var ajax = new $ax(Feng.ctxPath + "/role/delete", function () {
            	if(data.code == 1){
	                Feng.success("删除成功!");
	                Role.table.refresh();
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

        Feng.confirm("是否删除角色 " + Role.seItem.name + "?",operation);
    }
};

/**
 * 权限配置
 */
Role.assign = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '权限配置',
            area: ['300px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/role/assignPerm?roleId=' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 搜索角色
 */
Role.search = function () {
	var queryData = {};

    queryData['roleName'] = $("#roleName").val();

    Role.table.refresh({query: queryData});
}

$(function () {
    var defaultColunms = Role.initColumn();
//    var table = new BSTable(Role.id, "/role/list", defaultColunms);
//    table.setPaginationType("client");
    var table = new BSTable(Role.id, "/role/listByPage", defaultColunms);
    table.setMethod("get")
    table.init();
    Role.table = table;
});

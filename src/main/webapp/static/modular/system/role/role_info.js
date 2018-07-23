/**
 * 角色详情对话框（可用于添加和修改对话框）
 */
var RolInfoDlg = {
    roleInfoData: {},
    deptZtree: null,
    pNameZtree: null,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '角色名称不能为空'
                }
            }
        },
        tips: {
            validators: {
                notEmpty: {
                    message: '别名不能为空'
                }
            }
        }
//        ,
//        pName: {
//            validators: {
//                notEmpty: {
//                    message: '父级名称不能为空'
//                }
//            }
//        }
    }
};

/**
 * 清除数据
 */
RolInfoDlg.clearData = function () {
    this.roleInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RolInfoDlg.set = function (key, val) {
    this.roleInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RolInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
RolInfoDlg.close = function () {
    parent.layer.close(window.parent.Role.layerIndex);
};

/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
RolInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#deptName").attr("value", RolInfoDlg.deptZtree.getSelectedVal());
    $("#deptid").attr("value", treeNode.id);
};
RolInfoDlg.onDblClickDept = function (e, treeId, treeNode) {
    $("#deptName").attr("value", RolInfoDlg.deptZtree.getSelectedVal());
    $("#deptid").attr("value", treeNode.id);
    $("#deptContent").fadeOut("fast");
};

/**
 * 点击父级菜单input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
RolInfoDlg.onClickPName = function (e, treeId, treeNode) {
    $("#pName").attr("value", RolInfoDlg.pNameZtree.getSelectedVal());
    $("#pid").attr("value", treeNode.id);
};

/**
 * 显示部门选择的树
 *
 * @returns
 */
RolInfoDlg.showDeptSelectTree = function () {
    Feng.showInputTree("deptName", "deptContent");
};

/**
 * 显示父级菜单的树
 *
 * @returns
 */
RolInfoDlg.showPNameSelectTree = function () {
    Feng.showInputTree("pName", "pNameContent");
};

/**
 * 收集数据
 */
RolInfoDlg.collectData = function () {
    this.set('id').set('name').set('tips').set('num');//.set('pid').set('deptid')
};

/**
 * 验证数据是否为空
 */
RolInfoDlg.validate = function () {
    $('#roleInfoForm').data("bootstrapValidator").resetForm();
    $('#roleInfoForm').bootstrapValidator('validate');
    return $("#roleInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加用户
 */
RolInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/role/add", function (data) {
    	if(data.code == 1){
	        Feng.success("添加成功!");
	        window.parent.Role.table.refresh();
	        RolInfoDlg.close();
    	}else{
    		Feng.error("添加失败!"+data.message);
    	}
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.roleInfoData);
    ajax.setBeanData();
    ajax.start();
};

/**
 * 提交修改
 */
RolInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/role/edit", function (data) {
    	if(data.code == 1){
	    	Feng.success("修改成功!");
	        window.parent.Role.table.refresh();
	        RolInfoDlg.close();
    	}else{
    		Feng.error("修改失败!"+data.message);
    	}
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.roleInfoData);
    ajax.setBeanData();
    ajax.start();
};

/**
 * 获取角色信息详情
 */
RolInfoDlg.getRoleDetail = function () {
	var id = $("#id").val();
    if(id == null || id == 'undefined' || id == ''){
    	return;
    }
    var data = {};
	data['id'] = id;
	//提交请求
    var ajax = new $ax(Feng.ctxPath + "/role/getDetail", function (data) {
    	if(data.code == 1){
     	   $("#name").val(data.data.name);
     	   $("#tips").val(data.data.tips);
     	   $("#num").val(data.data.num);
    	}
    }, function (data) {
    	;
    });
    ajax.setType('get');
    ajax.set(data);
    ajax.start();
};

$(function () {
    Feng.initValidator("roleInfoForm", RolInfoDlg.validateFields);
    
    //如果是修改的话，获取详情
    RolInfoDlg.getRoleDetail();

//    var deptTree = new $ZTree("deptTree", "/dept/tree");
//    deptTree.bindOnClick(RolInfoDlg.onClickDept);
//    deptTree.bindOnDblClick(RolInfoDlg.onDblClickDept)
//    deptTree.init();
//    RolInfoDlg.deptZtree = deptTree;

//    var pNameTree = new $ZTree("pNameTree", "/role/roleTreeList");
//    pNameTree.bindOnClick(RolInfoDlg.onClickPName);
//    pNameTree.init();
//    RolInfoDlg.pNameZtree = pNameTree;
});

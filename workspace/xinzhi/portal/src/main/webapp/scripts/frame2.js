var navtab;
$(function() {
	$("#layout1").ligerLayout({leftWidth: 200}); 
	navtab = $("#navtab1").ligerTab({
		height: '100%',
         showSwitch: true,
         ShowSwitchInTab: true,
         changeHeightOnResize: true
     });
});
function newNavtab(id, text, url) {
	navtab.addTabItem({
		tabid : id,
		text : text,
		url : url,
		callback : function() {
		}
	});
}
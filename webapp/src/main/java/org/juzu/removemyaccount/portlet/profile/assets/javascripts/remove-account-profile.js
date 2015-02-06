/**
 * Created by exoplatform on 15/01/15.
 */
(function($){
  var _nbRelocation = 0;
  var _reLocated = function(){
    var btnContainerDOM = $(".juzRemoveAccountProfilePortletContainer").parent().parent();
    var profileDOM = $('.uiExperienceSection');
    var btnId = btnContainerDOM.attr("id");
    _nbRelocation++;
    if(_nbRelocation < 10){
      if(profileDOM.length > 0){
        if(profileDOM.children(btnId).length <= 0) {
          btnContainerDOM.appendTo(profileDOM);
        }else{
          _reLocated();
        }
       }else{
        _reLocated();
      }
    }
  };
  $(document).ready(function () {
    _reLocated();
  });
})($);
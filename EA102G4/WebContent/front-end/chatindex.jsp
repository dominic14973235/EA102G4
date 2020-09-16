<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<style type="text/css">
html, body {
	font: 15px verdana, Times New Roman, arial, helvetica, sans-serif, Microsoft JhengHei;
	width: 90%;
	height: 90%;
	background: #eeeeda;
}

#userName {
	position: absolute;
	top: 50%;
	left: 50%;
	height: 30px;
	width: 250px;
	margin: -50px 121px 0 -130px;
}

#outPopUp {
	position: absolute;
	width: 500px;
	height: 300px;
	z-index: 15;
	top: 50%;
	left: 50%;
	margin: -200px 250px 0 -250px;
}

.button {
	background-color: #0078ae;
	color: #ffffff;
	border-radius: 5px;
	position: absolute;
	width: 100px;
	height: 40px; 
	top : 50%;
	left: 50%;
	top: 50%; 
	left : 50%;
	margin: 20px 200px 0 -50px;
}
</style>
<title>Join Us</title>
     <!-- favicon -->
     <link rel=icon href=assets/img/favicon.png sizes="20x20" type="image/png">
    <!-- animate -->
    <link rel="stylesheet" href="assets/css/animate.css">
    <!-- bootstrap -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <!-- magnific popup -->
    <link rel="stylesheet" href="assets/css/magnific-popup.css">
    <!-- Slick -->
    <link rel="stylesheet" href="assets/css/slick.css" />
    <link rel="stylesheet" href="assets/css/slick-theme.css" />
    <!-- nice select -->
    <link rel="stylesheet" href="assets/css/nice-select.css"> 
    <!-- owl carousel -->
    <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
    <!-- fontawesome -->
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">
    <!-- flaticon -->
    <link rel="stylesheet" href="assets/css/flaticon.css">
    <!-- hamburgers -->
    <link rel="stylesheet" href="assets/css/hamburgers.min.css">
    <!-- hamburgers -->
    <link rel="stylesheet" href="assets/css/hamburgers.min.css">
    <!-- twentytwenty -->
    <link rel="stylesheet" href="assets/css/twentytwenty.css">
    <!-- Date Picker -->
    <link href="assets/css/datepicker.min.css" rel="stylesheet" type="text/css">
    <!-- Time Picker -->
    <link rel="stylesheet" href="assets/css/wickedpicker.min.css">
    <!-- Main Stylesheet -->
    <link rel="stylesheet" href="assets/css/style.css">
    <!-- responsive Stylesheet -->
    <link rel="stylesheet" href="assets/css/responsive.css">
    <style>
    @import url(https://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic&subset=latin,cyrillic);
    @import url(https://zavoloklom.github.io/material-design-iconic-font/css/docs.md-iconic-font.min.css);

    ul li {
        list-style: none;
    }

    .fabs {
        bottom: 0;
        position: fixed;
        margin: 1em;
        right: 0;
        z-index: 998;
    }

    .fab {
        display: block;
        width: 56px;
        height: 56px;
        border-radius: 50%;
        text-align: center;
        color: #f0f0f0;
        margin: 25px auto 0;
        box-shadow: 0 0 4px rgba(0, 0, 0, .14), 0 4px 8px rgba(0, 0, 0, .28);
        cursor: pointer;
        -webkit-transition: all .1s ease-out;
        transition: all .1s ease-out;
        position: relative;
        z-index: 998;
        overflow: hidden;
        background: #42a5f5;
    }

    .fab>i {
        font-size: 2em;
        line-height: 55px;
        -webkit-transition: all .2s ease-out;
        -webkit-transition: all .2s ease-in-out;
        transition: all .2s ease-in-out;
    }

    .fab:not(:last-child) {
        width: 0;
        height: 0;
        margin: 20px auto 0;
        opacity: 0;
        visibility: hidden;
        line-height: 40px;
    }

    .fab:not(:last-child)>i {
        font-size: 1.4em;
        line-height: 40px;
    }

    .fab:not(:last-child).is-visible {
        width: 40px;
        height: 40px;
        margin: 15px auto 10;
        opacity: 1;
        visibility: visible;
    }

    .fab:nth-last-child(1) {
        -webkit-transition-delay: 25ms;
        transition-delay: 25ms;
    }

    .fab:not(:last-child):nth-last-child(2) {
        -webkit-transition-delay: 20ms;
        transition-delay: 20ms;
    }

    .fab:not(:last-child):nth-last-child(3) {
        -webkit-transition-delay: 40ms;
        transition-delay: 40ms;
    }

    .fab:not(:last-child):nth-last-child(4) {
        -webkit-transition-delay: 60ms;
        transition-delay: 60ms;
    }

    .fab:not(:last-child):nth-last-child(5) {
        -webkit-transition-delay: 80ms;
        transition-delay: 80ms;
    }

    .fab(:last-child):active,
    .fab(:last-child):focus,
    .fab(:last-child):hover {
        box-shadow: 0 0 6px rgba(0, 0, 0, .16), 0 6px 12px rgba(0, 0, 0, .32);
    }

    /*Chatbox*/

    .chat {
        position: fixed;
        right: 85px;
        bottom: 20px;
        width: 400px;
        font-size: 12px;
        line-height: 22px;
        font-family: 'Roboto';
        font-weight: 500;
        -webkit-font-smoothing: antialiased;
        font-smoothing: antialiased;
        opacity: 0;
        box-shadow: 1px 1px 100px 2px rgba(0, 0, 0, 0.22);
        border-radius: 10px;
        -webkit-transition: all .2s ease-out;
        -webkit-transition: all .2s ease-in-out;
        transition: all .2s ease-in-out;
    }

    .chat_fullscreen {
        position: fixed;
        right: 0px;
        bottom: 0px;
        top: 0px;
    }

    .chat_header {
        /* margin: 10px; */
        font-size: 13px;
        font-family: 'Roboto';
        font-weight: 500;
        color: #f3f3f3;
        height: 55px;
        background: #42a5f5;
        border-top-left-radius: 10px;
        border-top-right-radius: 10px;
        padding-top: 8px;
    }

    .chat_header2 {
        /* margin: 10px; */
        border-top-left-radius: 0px;
        border-top-right-radius: 0px;
    }

    .chat_header .span {
        float: right;
    }

    .chat_fullscreen_loader {
        display: block;
        float: right;
        cursor: pointer;
        /* margin: 10px; */
        font-size: 20px;
        opacity: 0.5;
        /* padding: 20px; */
        margin: -10px 10px;
    }

    .chat.is-visible {
        opacity: 1;
        -webkit-animation: zoomIn .2s cubic-bezier(.42, 0, .58, 1);
        animation: zoomIn .2s cubic-bezier(.42, 0, .58, 1);
    }

    .is-hide {
        opacity: 0
    }

    .chat_option {
        float: left;
        font-size: 15px;
        list-style: none;
        position: relative;
        height: 100%;
        width: 100%;
        text-align: relative;
        margin-right: 10px;
        letter-spacing: 0.5px;
        font-weight: 400
    }


    .chat_option img {
        border-radius: 50%;
        width: 55px;
        float: left;
        margin: -30px 20px 10px 20px;
        border: 4px solid rgba(0, 0, 0, 0.21);
    }

    .change_img img {
        width: 35px;
        margin: 0px 20px 0px 20px;
    }

    .chat_option .agent {
        font-size: 12px;
        font-weight: 300;
    }

    .chat_option .online {
        opacity: 0.4;
        font-size: 11px;
        font-weight: 300;
    }

    .chat_color {
        display: block;
        width: 20px;
        height: 20px;
        border-radius: 50%;
        margin: 10px;
        float: left;
    }


    .chat_body {
        background: #fff;
        width: 100%;

        display: inline-block;
        text-align: center;
        overflow-y: auto;

    }

    #chat_body {
        height: 450px;
    }

    .chat_login p,
    .chat_body li,
    p,
    a {
        -webkit-animation: zoomIn .5s cubic-bezier(.42, 0, .58, 1);
        animation: zoomIn .5s cubic-bezier(.42, 0, .58, 1);
    }

    .chat_body p {
        padding: 20px;
        color: #888
    }

    .chat_body a {
        width: 10%;
        text-align: center;
        border: none;
        box-shadow: none;
        line-height: 40px;
        font-size: 15px;
    }

    .chat_field {
        position: relative;
        margin: 5px 0 5px 0;
        width: 50%;
        font-family: 'Roboto';
        font-size: 12px;
        line-height: 30px;
        font-weight: 500;
        color: #4b4b4b;
        -webkit-font-smoothing: antialiased;
        font-smoothing: antialiased;
        border: none;
        outline: none;
        display: inline-block;
    }

    .chat_field.chat_message {
        height: 30px;
        resize: none;
        font-size: 13px;
        font-weight: 400;
    }

    .chat_category {
        text-align: left;
    }

    .chat_category {
        margin: 20px;
        background: rgba(0, 0, 0, 0.03);
        padding: 10px;
    }

    .chat_category ul li {
        width: 80%;
        height: 30px;
        background: #fff;
        padding: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        margin-bottom: 10px;
        border-radius: 3px;
        border: 1px solid #e0e0e0;
        font-size: 13px;
        cursor: pointer;
        line-height: 30px;
        color: #888;
        text-align: center;
    }

    .chat_category li:hover {
        background: #83c76d;
        color: #fff;
    }

    .chat_category li.active {
        background: #83c76d;
        color: #fff;
    }

    .tags {
        margin: 20px;
        bottom: 0px;
        display: block;
        width: 120%
    }

    .tags li {
        padding: 5px 10px;
        border-radius: 40px;
        border: 1px solid rgb(3, 117, 208);
        margin: 5px;
        display: inline-block;
        color: rgb(3, 117, 208);
        cursor: pointer;
    }

    .fab_field {
        width: 100%;
        display: inline-block;
        text-align: center;
        background: #fff;
        border-top: 1px solid #eee;
        border-bottom-right-radius: 10px;
        border-bottom-left-radius: 10px;
    }

    .fab_field2 {
        bottom: 0px;
        position: absolute;
        border-bottom-right-radius: 0px;
        border-bottom-left-radius: 0px;
        z-index: 999;
    }

    .fab_field a {
        display: inline-block;
        text-align: center;
    }

    #fab_camera {
        float: left;
        background: rgba(0, 0, 0, 0);
    }

    #fab_send {
        float: right;
        background: rgba(0, 0, 0, 0);
    }

    .fab_field .fab {
        width: 35px;
        height: 35px;
        box-shadow: none;
        margin: 5px;
    }

    .fab_field .fab>i {
        font-size: 1.6em;
        line-height: 35px;
        color: #bbb;
    }

    .fab_field .fab>i:hover {
        color: #42a5f5;
    }

    .chat_conversion {}

    .chat_converse {
        position: relative;
        background: #fff;
        margin: 6px 0 0px 0;
        height: 300px;
        min-height: 0;
        font-size: 12px;
        line-height: 18px;
        overflow-y: auto;
        width: 100%;
        float: right;
        padding-bottom: 100px;
    }

    .chat_converse2 {
        height: 100%;
        max-height: 800px
    }

    .chat_list {
        opacity: 0;
        visibility: hidden;
        height: 0;
    }

    .chat_list .chat_list_item {
        opacity: 0;
        visibility: hidden;
    }

    .chat .chat_converse .chat_msg_item {
        position: relative;
        margin: 8px 0 15px 0;
        padding: 8px 10px;
        max-width: 60%;
        display: block;
        word-wrap: break-word;
        border-radius: 3px;
        -webkit-animation: zoomIn .5s cubic-bezier(.42, 0, .58, 1);
        animation: zoomIn .5s cubic-bezier(.42, 0, .58, 1);
        clear: both;
        z-index: 999;
    }

     .status { 
         margin: 45px -50px 0 0; 
         float: right; 
         font-size: 11px;
         opacity: 0.3; 
     }

	.status {
		font-size: 11px;
	}
	
	.chat_msg_item_admin + .status {
	    clear: both;
	    float: left;
	    margin-top: 0;
	    margin-left: 20px;
	}

	.chat_msg_item_user + .status {
		clear: both;
	    float: right;
	    margin-top: 0;
	    margin-right: 20px;
	}

    .status2 {
        margin: -10px 20px 0 0;
        float: right;
        display: block;
        font-size: 11px;
        opacity: 0.3;
    }

    .chat .chat_converse .chat_msg_item .chat_avatar {
        position: absolute;
        top: 0;
    }

    .chat .chat_converse .chat_msg_item.chat_msg_item_admin .chat_avatar {
        left: -52px;
        background: rgba(0, 0, 0, 0.03);
    }

    .chat .chat_converse .chat_msg_item.chat_msg_item_user .chat_avatar {
        right: -52px;
        background: rgba(0, 0, 0, 0.6);
    }

    .chat .chat_converse .chat_msg_item .chat_avatar,
    .chat_avatar img {
        width: 40px;
        height: 40px;
        text-align: center;
        border-radius: 50%;
    }

    .chat .chat_converse .chat_msg_item.chat_msg_item_admin {
        margin-left: 60px;
        float: left;
        background: rgba(0, 0, 0, 0.03);
        color: #666;
    }

    .chat .chat_converse .chat_msg_item.chat_msg_item_user {
        margin-right: 20px;
        float: right;
        background: #42a5f5;
        color: #eceff1;
    }

    .chat .chat_converse .chat_msg_item.chat_msg_item_admin:before {
        content: '';
        position: absolute;
        top: 15px;
        left: -12px;
        z-index: 998;
        border: 6px solid transparent;
        border-right-color: rgba(255, 255, 255, .4);
    }

    .chat_form .get-notified label {
        color: #077ad6;
        font-weight: 600;
        font-size: 11px;
    }

    input {
        position: relative;
        width: 90%;
        font-family: 'Roboto';
        font-size: 12px;
        line-height: 20px;
        font-weight: 500;
        color: #4b4b4b;
        -webkit-font-smoothing: antialiased;
        font-smoothing: antialiased;
        outline: none;
        background: #fff;
        display: inline-block;
        resize: none;
        padding: 5px;
        border-radius: 3px;
    }

    .chat_form .get-notified input {
        margin: 2px 0 0 0;
        border: 1px solid #83c76d;
    }

    .chat_form .get-notified i {
        background: #83c76d;
        width: 30px;
        height: 32px;
        font-size: 18px;
        color: #fff;
        line-height: 30px;
        font-weight: 600;
        text-align: center;
        margin: 2px 0 0 -30px;
        position: absolute;
        border-radius: 3px;
    }

    .chat_form .message_form {
        margin: 10px 0 0 0;
    }

    .chat_form .message_form input {
        margin: 5px 0 5px 0;
        border: 1px solid #e0e0e0;
    }

    .chat_form .message_form textarea {
        margin: 5px 0 5px 0;
        border: 1px solid #e0e0e0;
        position: relative;
        width: 90%;
        font-family: 'Roboto';
        font-size: 12px;
        line-height: 20px;
        font-weight: 500;
        color: #4b4b4b;
        -webkit-font-smoothing: antialiased;
        font-smoothing: antialiased;
        outline: none;
        background: #fff;
        display: inline-block;
        resize: none;
        padding: 5px;
        border-radius: 3px;
    }

    .chat_form .message_form button {
        margin: 5px 0 5px 0;
        border: 1px solid #e0e0e0;
        position: relative;
        width: 95%;
        font-family: 'Roboto';
        font-size: 12px;
        line-height: 20px;
        font-weight: 500;
        color: #fff;
        -webkit-font-smoothing: antialiased;
        font-smoothing: antialiased;
        outline: none;
        background: #fff;
        display: inline-block;
        resize: none;
        padding: 5px;
        border-radius: 3px;
        background: #83c76d;
        cursor: pointer;
    }

    strong.chat_time {
        padding: 0 1px 1px 0;
        font-weight: 500;
        font-size: 8px;
        display: block;
    }

    /*Chatbox scrollbar*/

    ::-webkit-scrollbar {
        width: 6px;
    }

    ::-webkit-scrollbar-track {
        border-radius: 0;
    }

    ::-webkit-scrollbar-thumb {
        margin: 2px;
        border-radius: 10px;
        background: rgba(0, 0, 0, 0.2);
    }

    /*Element state*/

    .is-active {
        -webkit-transform: rotate(180deg);
        transform: rotate(180deg);
        -webkit-transition: all 1s ease-in-out;
        transition: all 1s ease-in-out;
    }

    .is-float {
        box-shadow: 0 0 6px rgba(0, 0, 0, .16), 0 6px 12px rgba(0, 0, 0, .32);
    }

    .is-loading {
        display: block;
        -webkit-animation: load 1s cubic-bezier(0, .99, 1, 0.6) infinite;
        animation: load 1s cubic-bezier(0, .99, 1, 0.6) infinite;
    }

    /*Animation*/

    @-webkit-keyframes zoomIn {
        0% {
            -webkit-transform: scale(0);
            transform: scale(0);
            opacity: 0.0;
        }
        100% {
            -webkit-transform: scale(1);
            transform: scale(1);
            opacity: 1;
        }
    }

    @keyframes zoomIn {
        0% {
            -webkit-transform: scale(0);
            transform: scale(0);
            opacity: 0.0;
        }
        100% {
            -webkit-transform: scale(1);
            transform: scale(1);
            opacity: 1;
        }
    }

    @-webkit-keyframes load {
        0% {
            -webkit-transform: scale(0);
            transform: scale(0);
            opacity: 0.0;
        }
        50% {
            -webkit-transform: scale(1.5);
            transform: scale(1.5);
            opacity: 1;
        }
        100% {
            -webkit-transform: scale(1);
            transform: scale(1);
            opacity: 0;
        }
    }

    @keyframes load {
        0% {
            -webkit-transform: scale(0);
            transform: scale(0);
            opacity: 0.0;
        }
        50% {
            -webkit-transform: scale(1.5);
            transform: scale(1.5);
            opacity: 1;
        }
        100% {
            -webkit-transform: scale(1);
            transform: scale(1);
            opacity: 0;
        }
    }

    /* SMARTPHONES PORTRAIT */

    @media only screen and (min-width: 300px) {
        .chat {
            width: 250px;
        }
    }

    /* SMARTPHONES LANDSCAPE */

    @media only screen and (min-width: 480px) {
        .chat {
            width: 300px;
        }
        .chat_field {
            width: 65%;
        }
    }

    /* TABLETS PORTRAIT */

    @media only screen and (min-width: 768px) {
        .chat {
            width: 300px;
        }
        .chat_field {
            width: 65%;
        }
    }

    /* TABLET LANDSCAPE / DESKTOP */

    @media only screen and (min-width: 1024px) {
        .chat {
            width: 300px;
        }
        .chat_field {
            width: 65%;
        }
    }

    /*Color Options*/



    .blue .fab {
        background: #42a5f5;
        color: #fff;
    }



    .blue .chat {
        background: #42a5f5;
        color: #999;
    }


    /* Ripple */

    .ink {
        display: block;
        position: absolute;
        background: rgba(38, 50, 56, 0.4);
        border-radius: 100%;
        -moz-transform: scale(0);
        -ms-transform: scale(0);
        webkit-transform: scale(0);
        -webkit-transform: scale(0);
        transform: scale(0);
    }

    /*animation effect*/

    .ink.animate {
        -webkit-animation: ripple 0.5s ease-in-out;
        animation: ripple 0.5s ease-in-out;
    }

    @-webkit-keyframes ripple {
        /*scale the element to 250% to safely cover the entire link and fade it out*/

        100% {
            opacity: 0;
            -moz-transform: scale(5);
            -ms-transform: scale(5);
            webkit-transform: scale(5);
            -webkit-transform: scale(5);
            transform: scale(5);
        }
    }

    @keyframes ripple {
        /*scale the element to 250% to safely cover the entire link and fade it out*/

        100% {
            opacity: 0;
            -moz-transform: scale(5);
            -ms-transform: scale(5);
            webkit-transform: scale(5);
            -webkit-transform: scale(5);
            transform: scale(5);
        }
    }

    ::-webkit-input-placeholder {
        /* Chrome */
        color: #bbb;
    }

    :-ms-input-placeholder {
        /* IE 10+ */
        color: #bbb;
    }

    ::-moz-placeholder {
        /* Firefox 19+ */
        color: #bbb;
    }

    :-moz-placeholder {
        /* Firefox 4 - 18 */
        color: #bbb;
    }
    
div#friendGroup {
    position: fixed;
    right: 385px;
    height: 358px;
}

#row h1 {
    font-size: 15px;
    font-weight: 600;
    font-family: microsoft JhengHei;
    margin: 0px 0px 0 0px;
    top: -8px;
    position: relative;
}

#row img {
    border-radius: 50%;
    width: 55px;
    float: left;
}

.memberID {
	margin: -1px 0 0 0px;
    border-bottom: 1px solid lightgray;
    border-top: 1px solid lightgray;
    height: 65px;
}

h5.friendHistory {
    font-size: 25px;
    margin: 15px 0 14px 10px;
}

.people {
    position: relative;
    top: 5px;
    left: 5px;
}

.column {
    position: relative;
    left: 6px;
    top: 5px;
}

.chat_option img {
    margin: -7px 0 0 5px;
}

span#chat_head {
	font-family: microsoft JhengHei;
    font-size: 25px;
    position: relative;
    top: 9px;
    left: 9px;
}

    </style>

</head>
<body onload="connectChat();" onunload="disconnectChat();">
	<div id="outPopUp">
		<h1 align="center">tibame中壢最大的私聊上線囉～</h1>
		<form id="myForm" action="chat.do" method="POST">
			<input id="userName" name="userName" class="text-field" type="text" placeholder="Input user name" /> 
			<input type="submit" id="send" class="button" value="送出" onclick="sendName();" />
		</form>
	</div>
	
	<div id="friendGroup" class="chat">
		<h5 class="friendHistory">Members<sub class="online" style="font-size: 50%;">(Online)</sub></h5>
		<div id="row"></div>
	</div>
    <div class="chatBox">
	    <div class="fabs">
	        <div class="chat">
	            <div class="chat_header">
	                <div class="chat_option">
	                    <div class="header_img">
	                        <img src="http://res.cloudinary.com/dqvwa7vpe/image/upload/v1496415051/avatar_ma6vug.jpg" />
	                    </div>
	                    <span id="chat_head"></span> <br>
	                    <span id="chat_fullscreen_loader" class="chat_fullscreen_loader"><i class="fullscreen zmdi zmdi-window-maximize"></i></span>
	                </div>
	            </div>
	
	            <div id="chat_fullscreen" class="chat_conversion chat_converse">
	                
	            </div>
	
	            <div class="fab_field">
	                <a id="fab_camera" class="fab"><i class="zmdi zmdi-camera"></i><input type="file" id="imageUpload" name="imageUpload" style="display:none;"></a>
	                <a id="fab_send" class="fab" onclick="sendChatMessage();"><i class="zmdi zmdi-mail-send"></i></a>
	                <input type="text" id="chatSend" name="chat_message" placeholder="Send a message" class="chat_field chat_message" onkeydown="if (event.keyCode == 13) sendChatMessage();">
	            </div>
	        </div>
	        <a id="prime" class="fab"><i class="prime zmdi zmdi-comment-outline"></i></a>
	    </div>
    </div>
</body>
<script>
	var inputUserName = document.getElementById("userName");
	inputUserName.focus();
	
	function sendName() {
		var userName = inputUserName.value.trim();
		if (userName === "") {
			alert("Input a user name");
			inputUserName.focus();
			return;
		} else {
			document.getElementById("myForm").submit();
		}
	}
</script>

    <!-- jquery -->
    <script src="assets/js/jquery-2.2.4.min.js"></script>
    <!-- popper -->
    <script src="assets/js/popper.min.js"></script>
    <!-- bootstrap -->
    <script src="assets/js/bootstrap.min.js"></script>
    <!-- magnific popup -->
    <script src="assets/js/jquery.magnific-popup.js"></script>
    <!-- wow -->
    <script src="assets/js/wow.min.js"></script>
    <!-- nice select -->
    <script src="assets/js/nice-select.js"></script>
    <!-- owl carousel -->
    <script src="assets/js/owl.carousel.min.js"></script>
    <!-- Slick -->
    <script src="assets/js/slick.min.js"></script>
    <!-- Slick Animation -->
    <script src="assets/js/slick-animation.js"></script>
    <!-- jquery.twentytwenty -->
    <script src="assets/js/jquery.twentytwenty.js"></script>
    <script src="assets/js/jquery.event.move.js"></script>
    <!-- waypoint -->
    <script src="assets/js/waypoints.min.js"></script>
     <!-- Date Picker -->
     <script src="assets/js/datepicker.min.js"></script>
     <script src="assets/js/datepicker.en.js"></script>
     <!-- Time Picker -->
     <script src="assets/js/wickedpicker.min.js"></script>
    <!-- counterup -->
    <script src="assets/js/jquery.counterup.min.js"></script>
    <!-- imageloaded -->
    <script src="assets/js/imagesloaded.pkgd.min.js"></script>
    <!-- isotope -->
    <script src="assets/js/isotope.pkgd.min.js"></script>
     <!-- rProgressbar -->
    <script src="assets/js/jQuery.rProgressbar.min.js"></script>
    <script src="assets/js/timepicker.js"></script>
    <!-- main js -->
    <script src="assets/js/main.js"></script>
    <script src="assets/js/script.js"></script>    
    
    <script>
    hideChat(0);

    $('#prime').click(function() {
        toggleFab();
    });

    //Toggle chat and links
    function toggleFab() {
        $('.prime').toggleClass('zmdi-comment-outline');
        $('.prime').toggleClass('zmdi-close');
        $('.prime').toggleClass('is-active');
        $('.prime').toggleClass('is-visible');
        $('#prime').toggleClass('is-float');
        $('.chat').toggleClass('is-visible');
        $('.fab').toggleClass('is-visible');
        $('div#friendGroup').attr('class',"chat");

        $('#chat_converse').css('display', 'none');
        $('#chat_body').css('display', 'none');
        $('#chat_form').css('display', 'none');
        $('.chat_login').css('display', 'none');
        $('.chat_fullscreen_loader').css('display', 'block');
        $('#chat_fullscreen').css('display', 'block');

    }
    $('#chat_fullscreen_loader').click(function(e) {
    	$('div#friendGroup').toggleClass('is-visible');
    });

    function hideChat(hide) {
        switch (hide) {
            case 0:
                $('#chat_converse').css('display', 'none');
                $('#chat_body').css('display', 'none');
                $('#chat_form').css('display', 'none');
                $('.chat_login').css('display', 'block');
                $('.chat_fullscreen_loader').css('display', 'none');
                $('#chat_fullscreen').css('display', 'none');
                break;
        }
    }
    </script>
    
    <script>
	var MyPoint = "/FriendWS/${userName}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	console.log(endPointURL);

	var chat_head = document.getElementById("chat_head");
	var messagesArea = document.getElementById("chat_fullscreen");
	var self = '${userName}';
	var webSocket;
	let friendOnlineArray = [];
	
	function connectChat() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);
		webSocket.onopen = function(event) {
			console.log("Connect Success!");
		};
	
		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("open" === jsonObj.type && jsonObj.user === self) {
				refreshFriendList(jsonObj);
			} else if ("history" === jsonObj.type) {
				messagesArea.innerHTML = '';
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					console.log(historyData);
					var messageTime = historyData.messageTime;
					var showMsg = historyData.message;
					var message;
					// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
					if(historyData.sender === self){
						message = '';
						message = '<span class="chat_msg_item chat_msg_item_user">'+showMsg+'</span><div class="status">'+messageTime+'</div>';
						$('#chat_fullscreen').append(message);
					} else{
						console.log("history other");
						message = '';
						message = 
							  '<span class="chat_msg_item chat_msg_item_admin">'+
						          '<div class="chat_avatar">'+
						              '<img src="http://res.cloudinary.com/dqvwa7vpe/image/upload/v1496415051/avatar_ma6vug.jpg" />'+
						          '</div>'+showMsg+
						      '</span>'+
						      '<div class="status">'+messageTime+'</div>';
						$('#chat_fullscreen').append(message);  
					}
				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj.type) {
				if(jsonObj.sender === self){
					message = '';
					message = '<span class="chat_msg_item chat_msg_item_user">'+jsonObj.message+'</span><div class="status">'+jsonObj.messageTime+'</div>';
					$('#chat_fullscreen').append(message);
					
					var listMessage = "#"+jsonObj.receiver+" #historyMessage";
					var listTime = "#"+jsonObj.receiver+" #historyTime";
					
					$(listMessage).text(jsonObj.message);
					$(listTime).text(jsonObj.messageTime);
					
				}else if($('#chat_head').text() != ""){
					message = '';
					message = 
						  '<span class="chat_msg_item chat_msg_item_admin">'+
					          '<div class="chat_avatar">'+
					              '<img src="http://res.cloudinary.com/dqvwa7vpe/image/upload/v1496415051/avatar_ma6vug.jpg" />'+
					          '</div>'+jsonObj.message+
					      '</span>'+
					      '<div class="status">'+jsonObj.messageTime+'</div>';
					$('#chat_fullscreen').append(message);  
					
					var listMessage = "#"+jsonObj.sender+" #historyMessage";
					var listTime = "#"+jsonObj.sender+" #historyTime";
					
					$(listMessage).text(jsonObj.message);
					$(listTime).text(jsonObj.messageTime);
				} 
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("close" === jsonObj.type) {
// 				refreshFriendList(jsonObj);
			}
		};
		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}
	
	function sendChatMessage() {
		var now = new Date();
		var messageTime = now.toLocaleTimeString();
		var inputMessage = document.getElementById("chatSend");
		var friend = chat_head.textContent;
		var message = inputMessage.value.trim();
		
		console.log("sendChatMessage = "+message);

		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
		} else if (friend === "") {
			alert("Choose a friend");
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : friend,
				"message" : message,
				"messageTime" : messageTime
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	var friendsValue = [];
	// 有好友上線或離線就更新列表
	function refreshFriendList(jsonObj) {
		var friends = jsonObj.friends;
		friendsValue = jsonObj.friendsValue;
		
		var row = document.getElementById("row");
		row.innerHTML = '';
		for (var i = 0; i < friends.length; i++) {
			if (friends[i] === self) { continue; }
			row.innerHTML +='<div class="memberID"><div class="people"><img src="http://res.cloudinary.com/dqvwa7vpe/image/upload/v1496415051/avatar_ma6vug.jpg" /><div id=' + friends[i] + ' class="column" name="friendName" value=' + friends[i] + ' ><h1>' + friends[i] + '</h1></div></div></div>';
		}
		
		for(var i = 0;i<friendsValue.length;i++){
			if(JSON.parse(friendsValue[i]).sender != self){
				var name = "#"+JSON.parse(friendsValue[i]).sender;
				var nameMessage = "<p id='historyMessage' style='color: black; font-size: 12px; font-family: microsoft JhengHei; position: relative; top: -15px;'>"+JSON.parse(friendsValue[i]).message+"</p><p id='historyTime' style='color: black; font-size: 12px; font-family: microsoft JhengHei; position: relative; top: -56.5px; left: 200px;'>"+JSON.parse(friendsValue[i]).messageTime+"</p>";
				$(name).append(nameMessage);
				continue;
			}
			
			if(JSON.parse(friendsValue[i]).receiver != self)
				var name = "#"+JSON.parse(friendsValue[i]).receiver;
				var nameMessage = "<p id='historyMessage' style='color: black; font-size: 12px; font-family: microsoft JhengHei; position: relative; top: -15px;'>"+JSON.parse(friendsValue[i]).message+"</p><p id='historyTime' style='color: black; font-size: 12px; font-family: microsoft JhengHei; position: relative; top: -56.5px; left: 200px;'>"+JSON.parse(friendsValue[i]).messageTime+"</p>";
				$(name).append(nameMessage);
				continue;
		}
		addListener();
		clickAddListener();
	}
	
	function clickAddListener(){
		var container = document.getElementById("row");
		container.addEventListener("click", function(e) {
			var friend = e.srcElement.textContent;
			updateFriendName(friend);
			var jsonObj = {
					"type" : "history",
					"sender" : self,
					"receiver" : friend,
					"message" : "",
					"messageTime" : ""
				};
			webSocket.send(JSON.stringify(jsonObj));
		});
	}
	
	
	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
	function addListener() {
		var container = document.getElementById("row");
		container.addEventListener("click", function(e) {
			var friend = e.srcElement.textContent;
			updateFriendName(friend);
			var jsonObj = {
					"type" : "history",
					"sender" : self,
					"receiver" : friend,
					"message" : "",
					"messageTime" : ""
				};
			webSocket.send(JSON.stringify(jsonObj));
		});
	}
	
	function disconnectChat() {
		webSocket.close();
	}
	
	function updateFriendName(name) {
		console.log("updateFriendName_name = "+name);
		$('#chat_head').text(name);
	}
	
	$('.zmdi.zmdi-camera').click(function(){
		$('#fab_camera #imageUpload').click(); 
	});
	
	$('#fab_camera #imageUpload').change(function(){
		console.log("imageUpload Test");
	});
</script>

</html>
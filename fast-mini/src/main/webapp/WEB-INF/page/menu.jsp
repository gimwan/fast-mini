<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <div title="菜单缩放" class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
        <ul class="layui-nav layui-nav-tree" >
        	<li class="layui-nav-item layui-nav-itemed" v-bind:data-id="'#'+index" v-for="(m,index) in menu">
        		<a class="" href="javascript:void(0);">
                	<i class="fa fa-user-circle-o fa-lg"></i>
                	<span >{{m.name}}</span>
                </a>
                <dl class="layui-nav-child">
                    <dd v-for="(s,idx) in m.sub">
                    	<a href="javascript:void(0);" v-bind:data-id="'#'+index+'#'+idx" v-on:click="loadPage(s.link)">
                    		<i class="fa fa-list fa-lg"></i> 
                    		<span >{{s.name}}</span>
                    	</a>
                    </dd>
                </dl>
        	</li>
        </ul>
    </div>
</div>
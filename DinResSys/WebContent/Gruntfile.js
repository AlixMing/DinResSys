module.exports = function(grunt) {
	// Project configuration.
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		less: {
			compile: { // 编译单个文件
				files: { // 文件files 固定命名
					'css/main.css': 'less/main.less' // dest src
				}
			},
			dynamic: {		//编译多文件,目录自动生成
                expand: true,
                flatten: false,
                cwd: 'less',
                src: ['**/*.less'],
                dest: 'css/',
                ext: '.css'
            }
		},
		watch:{
			less: {
                files: ['less/**/*.less'],
                tasks: ['less:compile']
            }
		}
	});

	// 加载包含 "uglify" 任务的插件。
	grunt.loadNpmTasks('grunt-contrib-less');
	grunt.loadNpmTasks('grunt-contrib-watch');

	// 默认被执行的任务列表。
	grunt.registerTask('default', ['less:compile','watch']);
};
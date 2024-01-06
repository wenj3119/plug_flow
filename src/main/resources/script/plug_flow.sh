# Selection of color
red='\033[0;31m'
green='\033[0;32m'
yellow='\033[0;33m'
font="\033[0m"


stream_start(){
if [ ! $folder ];then
        folder="/data/bilibili"
fi
if [ ! $bv ];then
        bv="4000k"
fi
if [ ! $ba ];then
        ba="192k"
fi

# 判断是否需要添加水印
#read -p "是否需要为视频添加水印?水印位置默认在右上方,需要较好CPU支持. 默认不加,添加请输入y:" watermark
if [ $watermark = "y" ];then
        read -p "输入你的水印图片存放绝对路径,例如/opt/image/watermark.jpg (格式支持jpg/png/bmp):" image
        echo -e "${yellow} 添加水印完成,程序将开始推流. ${font}"
        # 循环
        while true
        do
                cd $folder
                video=$(find ./ -type f | shuf -n 1)
                ffmpeg -re -i "$video" -i "$image" -filter_complex overlay=W-w-5:5 -preset ultrafast  -g 25 -b:v "4000k" -c:a aac -b:a "192k" -strict -2 -f flv rtmp://live-push.bilivideo.com/live-bvc/?streamname=live_383277506_20729243&key=45ceac9f4b821c1de53b723e8fdd77be&schedule=rtmp&pflag=1
        done
else
    echo -e "${yellow} 你选择不添加水印,程序将开始推流. ${font}"
    # 循环
        while true
        do
                cd $folder
                video=$(find ./ -type f | shuf -n 1)
                ffmpeg -re -i "$video" -preset ultrafast -vcodec libx264 -g 25 -b:v "4000k" -c:a aac -b:a "192k" -strict -2 -f flv "rtmp://live-push.bilivideo.com/live-bvc/?streamname=live_383277506_20729243&key=45ceac9f4b821c1de53b723e8fdd77be&schedule=rtmp&pflag=1"
        done
fi
        }

# 停止推流
stream_stop(){
        screen -S bilive -X quit
        }

# 开始菜单设置
echo -e "${yellow} FFmpeg无人值守循环推流 ${font}"
echo -e "${red} 运行推流时请确定此脚本目前是在screen窗口内运行的! ${font}"
echo -e "${red} 运行推流时若未在screen窗口内请先运行  screen -S bilive ${font}"
echo -e "${green} 1.开始无人值守循环推流 ${font}"
echo -e "${green} 2.停止推流 ${font}"
start_menu(){
        stream_start
}
# 运行开始菜单
start_menu

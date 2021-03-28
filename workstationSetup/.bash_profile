
# _               _                        __ _ _      
#| |__   __ _ ___| |__    _ __  _ __ ___  / _(_) | ___ 
#| '_ \ / _` / __| '_ \  | '_ \| '__/ _ \| |_| | |/ _ \
#| |_) | (_| \__ \ | | | | |_) | | | (_) |  _| | |  __/
#|_.__/ \__,_|___/_| |_| | .__/|_|  \___/|_| |_|_|\___|
#                        |_|                           

alias q='QHOME=~/q rlwrap -r ~/q/m64/q'
export PATH=$PATH:/Users/Matthew/Library/Python/3.9/lib/python/site-packages
export PATH=$PATH:/Users/Matthew/Library/Python/2.7/lib/python/site-packages
export PATH=$PATH:/usr/local/sbin

source ~/.bashrc

 GRC=`which grc`
 if [ "$TERM" != dumb ] && [ -n "$GRC" ]
 then
     alias colourify='grc -es --colour=auto'
     alias blkid='colourify blkid'
     alias configure='colourify ./configure'
     alias df='colourify df'
     alias diff='colourify diff'
     alias docker='colourify docker'
     alias docker-machine='colourify docker-machine'
     alias du='colourify du'
     alias env='colourify env'
     alias free='colourify free'
     alias fdisk='colourify fdisk'
     alias findmnt='colourify findmnt'
     alias make='colourify make'
     alias gcc='colourify gcc'
     alias g++='colourify g++'
     alias id='colourify id'
     alias ip='colourify ip'
     alias iptables='colourify iptables'
     alias as='colourify as'
     alias gas='colourify gas'
     alias ld='colourify ld'
     #alias ls='colourify ls'
     alias lsof='colourify lsof'
     alias lsblk='colourify lsblk'
     alias lspci='colourify lspci'
     alias netstat='colourify netstat'
     alias ping='colourify ping'
     alias traceroute='colourify traceroute'
     alias traceroute6='colourify traceroute6'
     alias head='colourify head'
     alias tail='colourify tail'
     alias dig='colourify dig'
     alias mount='colourify mount'
     alias ps='colourify ps'
     alias mtr='colourify mtr'
     alias semanage='colourify semanage'
     alias getsebool='colourify getsebool'
     alias ifconfig='colourify ifconfig'
     alias getip='ipconfig getifaddr en0'

 fi

 alias cow="fortune | cowsay -n"
 alias PATH="sed 's/:/\n/g' <<< \"$PATH\"" 
 #zlib is not in CLT (command line tools. 
 #for pyenv, we must have it! Therefore it is installed via brew 
 #afterwards it is put in usr/local/opt. This is the place homebrew puts things so as not to overwrite defaults of the OS.
 #The system needs to know where it is, hence below.
 export LDFLAGS="-L/usr/local/opt/zlib/lib"
 export CPPFLAGS="-I/usr/local/opt/zlib/include"
 pyenv global 3.8.5
 if command -v pyenv 1>/dev/null 2>&1; then
   eval "$(pyenv init -)"
 fi

eval "$(rbenv init -)"

if [ -z "$TMUX" ]; then
      neofetch
    # not in tmux, do non-tmux things
fi

if [ "$TMUX_PANE" = "%0" ]; then
      neofetch      
fi    

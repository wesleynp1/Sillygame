#!/bin/bash
run(){
    mkdir -p ./classes &&
    rm -rf ./classes/* 
    javac -d ./classes/ ./src/main/java/com/wesleynp1/sillygame/*.java &&
    cd ./classes/ &&
    java com.wesleynp1.sillygame.SillyGame
}

build(){
    mkdir -p ./output &&
    rm -rf ./output/* && echo 'removidos builds antigas' &&
    javac -d ./classes/ ./src/main/java/com/wesleynp1/sillygame/*.java &&
    jar -c --file=./output/SillyGame.jar --main-class=com.wesleynp1.sillygame.SillyGame  -C ./classes/ com  && echo 'gerado novo .jar' &&
    chmod u+x ./output/* && echo 'autorizada execução do novo .jar'
}

test(){
    echo 'isso ainda ta sendo implementado...'
}

case $1 in 
    run)
        run
        ;;
    build)
        build
        ;;
    test)
        test
        ;;
    *)
        echo "comandos disponíveis: run, build, test"
        ;;
esac
    
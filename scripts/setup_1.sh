#!/bin/bash
cd ./mongodb_tools
sudo apt install ./mongodb-database-tools-ubuntu1804-x86_64-100.6.1.deb
line="nameserver 8.8.8.8"
sudo awk -v text="$line" '!/^#/ && !p {print text; p=1} 1' /etc/resolv.conf | sudo tee ./resolv.conf
sudo cp ./resolv.conf /etc/resolv.conf
sudo rm -rf ./resolv.conf
cd ../scripts
./download_JSON_files.sh
cd ..
python ./indexCreation.py

#!/bin/bash
#file 1
wget -O 1_fichero_jsonArray_A_ModifiedFields.json "https://drive.google.com/uc?export=download&id=1OE2SJY854qS1N6l5b7nZBGhJsCMLkSTg"
#file 2
wget --load-cookies /tmp/cookies.txt "https://docs.google.com/uc?export=download&confirm=$(wget --quiet --save-cookies /tmp/cookies.txt --keep-session-cookies --no-check-certificate 'https://docs.google.com/uc?export=download&id=1-Y_q-RAbt8GX_4zkvmkvwZh1GtuLmi0d' -O- | sed -rn 's/._confirm=([0-9A-Za-z_]+).\_/\1\n/p')&id=1-Y_q-RAbt8GX_4zkvmkvwZh1GtuLmi0d" -O 2_onboarding_countryC0.json && rm -rf /tmp/cookies.txt
#file 3
wget --load-cookies /tmp/cookies.txt "https://docs.google.com/uc?export=download&confirm=$(wget --quiet --save-cookies /tmp/cookies.txt --keep-session-cookies --no-check-certificate 'https://docs.google.com/uc?export=download&id=1P2lyqw251WBoXzTQ_caGYw2K48hjcBcV' -O- | sed -rn 's/._confirm=([0-9A-Za-z_]+).\_/\1\n/p')&id=1P2lyqw251WBoXzTQ_caGYw2K48hjcBcV" -O 3_onboarding_countryC1.json && rm -rf /tmp/cookies.txt
#file 4
wget --load-cookies /tmp/cookies.txt "https://docs.google.com/uc?export=download&confirm=$(wget --quiet --save-cookies /tmp/cookies.txt --keep-session-cookies --no-check-certificate 'https://docs.google.com/uc?export=download&id=1Us9shTp6WuPiDx-sBP1L-nGttwsBLHDC' -O- | sed -rn 's/._confirm=([0-9A-Za-z_]+).\_/\1\n/p')&id=1Us9shTp6WuPiDx-sBP1L-nGttwsBLHDC" -O 4_onboarding_countryC2.json && rm -rf /tmp/cookies.txt
#file 5
wget --load-cookies /tmp/cookies.txt "https://docs.google.com/uc?export=download&confirm=$(wget --quiet --save-cookies /tmp/cookies.txt --keep-session-cookies --no-check-certificate 'https://docs.google.com/uc?export=download&id=1XNtRHm19DqOOTIJSJxxElws9wPdpoOKg' -O- | sed -rn 's/._confirm=([0-9A-Za-z_]+).\_/\1\n/p')&id=1XNtRHm19DqOOTIJSJxxElws9wPdpoOKg" -O 5_onboarding_countryC3.json && rm -rf /tmp/cookies.txt
#file 6
wget --load-cookies /tmp/cookies.txt "https://docs.google.com/uc?export=download&confirm=$(wget --quiet --save-cookies /tmp/cookies.txt --keep-session-cookies --no-check-certificate 'https://docs.google.com/uc?export=download&id=1rXQ2JsoEA0eh-aRWoaUBeAUf7tdDnqIj' -O- | sed -rn 's/._confirm=([0-9A-Za-z_]+).\_/\1\n/p')&id=1rXQ2JsoEA0eh-aRWoaUBeAUf7tdDnqIj" -O 6_onboarding_countryC4.json && rm -rf /tmp/cookies.txt
#file 7
wget --load-cookies /tmp/cookies.txt "https://docs.google.com/uc?export=download&confirm=$(wget --quiet --save-cookies /tmp/cookies.txt --keep-session-cookies --no-check-certificate 'https://docs.google.com/uc?export=download&id=1S0Nw6FnmJxyo0KfNVO-STWVaRaHAfLOC' -O- | sed -rn 's/._confirm=([0-9A-Za-z_]+).\_/\1\n/p')&id=1S0Nw6FnmJxyo0KfNVO-STWVaRaHAfLOC" -O 7_onboarding_countryC3_actualizado.json && rm -rf /tmp/cookies.txt

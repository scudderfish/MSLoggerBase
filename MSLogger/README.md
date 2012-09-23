MSLogger
===============================================
MSLogger is an Android application for Megasquirt ECU ([MSExtra](http://www.msextra.com) - [MSEFI](http://msefi.com)).

Screenshots
------------------------------------------------
[![MSLogger Dashboard](http://img21.imageshack.us/img21/115/device20120715192842.png)](http://img21.imageshack.us/img21/115/device20120715192842.png) [![MSLogger Manage Datalogs](http://img35.imageshack.us/img35/3928/device20120715192857.png)](http://img35.imageshack.us/img35/3928/device20120715192857.png)  [![MSLogger Viewing Datalog](http://img716.imageshack.us/img716/1856/device20120715192949.png)](http://img716.imageshack.us/img716/1856/device20120715192949.png)

Megasquirt Firware Support
------------------------------------------------
MSLogger currently supports the following firmwares:

* megasquirt-I
* megasquirt-I_B&G_2.0-3.0
* megasquirt-I_B&G_2.0
* msns-extra.029q-29w
* msns-extra
* MSExtra format hr_10
* MSExtra format hr_11
* MSExtra format hr_11d
* megasquirt-I
* msns-extra.29y
* megasquirt-II.2.86
* megasquirt-II.2.88
* megasquirt-II.2.885
* megasquirt-II.2.886
* megasquirt-II
* megasquirt-II_3760
* megasquirt-II_v2905
* ms2extra.2.1p
* ms2extra.2.1q
* ms2extra_2.1.0.d
* ms2extra_3.03s
* ms2extra_3.03u
* ms2extra_3.03r
* MS2Extra Rel 2.0.1
* MS2Extra Rev 1.0.2
* MS2Extra311mb_v12
* MS2Extra Serial310
* MS2Extra Serial312a
* MS2Extra Serial321
* MS2Extra Serial323
* MS2Extra Serial330e
* MS2Extra Serial330f
* MS2Extra SerialGS26
* MS2Extra SerialGS27
* MS3 Format 0081.001
* MS3 Format 0089.001
* MS3 Format 0095.002
* MS3 Format 0095.005
* MS3 Format 0215.001
* MS3 Format 0221.002
* MS3 Format 0221.009
* MS3 Format 0225.005
* MS3 Format 0225.009
* MS3 Format 0225.010
* MS3 Format 0229.002
* MS3 Format 0231.006
* MS3 Format 0233.001
* MS3 Format 0233.003
* MS3 Format 0235.001
* MS3 Format 0237.000
* MS3 Format 0237.003
* MS3 Format 0238.000
* MS3 Format 0238.001
* MS3 Format 0238.002
* GPIO_MShift_2109

Please note that more firmwares can be supported upon request, just ask the developers.  List generated from Normaliser with the following command 

    grep ".ini" ../Normaliser/inis/generationList.txt |grep -v "^#" | sed -e's/.ini//' -e 's/^/\* /'

We can be contacted at msloggger.android@gmail.com
  
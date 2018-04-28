filelist=`cat $1`
for file in $filelist;
do
  echo $file
  ./script.sh $file > ../processed_data/$file.csv
done

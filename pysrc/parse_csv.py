import csv
import json
import re
# import numpy
import nltk
from nltk.stem import PorterStemmer
nltk.download('stopwords')
from nltk.corpus import stopwords


from nltk.tokenize import word_tokenize


def csv_to_json(csvFilePath, jsonFilePath):
    jsonArray = []

    # read csv file
    with open(csvFilePath) as csvf:
        # load csv file data using csv library's dictionary reader
        csvReader = csv.DictReader(csvf)

        # convert each csv row into python dict
        for row in csvReader:
            # add this python dict to json array
            jsonArray.append(row)

    # convert python jsonArray to JSON String and write to file
    with open(jsonFilePath, 'w') as jsonf:
        jsonString = json.dumps(jsonArray, indent=4)
        jsonf.write(jsonString)


def convert_json_to_csv(json_file_name):
    with open(json_file_name) as json_file:
        jsondata = json.load(json_file)

    data_file = open('formatted_resume_data.csv', 'w', newline='')
    csv_writer = csv.writer(data_file)

    count = 0
    for data in jsondata:
        if count == 0:
            header = data.keys()
            csv_writer.writerow(header)
            count += 1
        csv_writer.writerow(data.values())

    data_file.close()


def parse_json(file):
    f = open(file)
    data = json.load(f)
    final_json = {}
    formatted_data_json = []
    for resume_data in data:
        formatted_resume = dict()
        formatted_resume['Category'] = resume_data['Category']
        resume = resume_data['Resume']
        formatted_resume['Resume'] = format_data(resume)
        formatted_data_json.append(formatted_resume)
        # print(resume_data['Category'])
        # print(resume_data['Resume'])
    # print(data[0]['Resume'])
    # format_data(data[0]['Resume'])
    print(formatted_data_json)
    # final_json['data'] = formatted_data_json

    # Write to json file
    json_string = json.dumps(formatted_data_json)
    with open('formatted_resume_data.json', 'w') as outfile:
        outfile.write(json_string)

    convert_json_to_csv('formatted_resume_data.json')
    # return final_json


def format_data(resume_data):
    # Normalization
    resume_data = resume_data.replace('â', '')
    normalized_string = re.sub(r'\d+', '', resume_data.lower())
    normalized_string = re.sub(r'[^\w\s]', '', normalized_string).split()

    # Stopping
    stop_words = set(stopwords.words('english'))
    # print(stop_words)
    no_stpwords_string = ""
    for word in normalized_string:
        if word not in stop_words:
            no_stpwords_string += word + ' '

    no_stpwords_string = no_stpwords_string[:-1]
    # print(no_stpwords_string)

    # Stemming
    return stemming(no_stpwords_string.split())


def stemming(resume_data):
    ps = PorterStemmer()
    stemmed_string = ""
    for word in resume_data:
        stemmed_string += ps.stem(word) + ' '
    # print("stemmed_string: ", stemmed_string)
    return stemmed_string


csvFilePath = r'UpdatedResumeDataSet.csv'
jsonFilePath = r'UpdatedResumeDataSet.json'
csv_to_json(csvFilePath, jsonFilePath)
parse_json(jsonFilePath)



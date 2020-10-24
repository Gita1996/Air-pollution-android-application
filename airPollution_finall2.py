
# coding: utf-8

# In[ ]:


import pandas as pd
import numpy as np
from sklearn.preprocessing import LabelEncoder
from sklearn.preprocessing import Imputer
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.model_selection import train_test_split 
from sklearn.preprocessing import StandardScaler
from sklearn.neural_network import MLPClassifier
from sklearn.preprocessing import StandardScaler
train_df = pd.read_csv("C:\\Users\\HP\\train_pollution.csv")
train_df = train_df.replace(-200, np.NaN)
value_to_fill_embarked = train_df['CO(GT)'].dropna().mean()
train_df['CO(GT)'][ train_df['CO(GT)'].isnull() ] = value_to_fill_embarked

value_to_fill_embarked = train_df['PT08.S1(CO)'].dropna().mean()
train_df['PT08.S1(CO)'][ train_df['PT08.S1(CO)'].isnull() ] = value_to_fill_embarked

train_df.drop('NMHC(GT)', axis=1, inplace=True)

value_to_fill_embarked = train_df['C6H6(GT)'].dropna().mean()
train_df['C6H6(GT)'][ train_df['C6H6(GT)'].isnull() ] = value_to_fill_embarked

value_to_fill_embarked = train_df['PT08.S2(NMHC)'].dropna().mean()
train_df['PT08.S2(NMHC)'][ train_df['PT08.S2(NMHC)'].isnull() ] = value_to_fill_embarked

value_to_fill_embarked = train_df['NOx(GT)'].dropna().mean()
train_df['NOx(GT)'][ train_df['NOx(GT)'].isnull() ] = value_to_fill_embarked


value_to_fill_embarked = train_df['PT08.S3(NOx)'].dropna().mean()
train_df['PT08.S3(NOx)'][ train_df['PT08.S3(NOx)'].isnull() ] = value_to_fill_embarked

value_to_fill_embarked = train_df['NO2(GT)'].dropna().mean()
train_df['NO2(GT)'][ train_df['NO2(GT)'].isnull() ] = value_to_fill_embarked

value_to_fill_embarked = train_df['PT08.S4(NO2)'].dropna().mean()
train_df['PT08.S4(NO2)'][ train_df['PT08.S4(NO2)'].isnull() ] = value_to_fill_embarked

value_to_fill_embarked = train_df['PT08.S5(O3)'].dropna().mean()
train_df['PT08.S5(O3)'][ train_df['PT08.S5(O3)'].isnull() ] = value_to_fill_embarked

value_to_fill_embarked = train_df['T'].dropna().mean()
train_df['T'][ train_df['T'].isnull() ] = value_to_fill_embarked

value_to_fill_embarked = train_df['RH'].dropna().mean()
train_df['RH'][ train_df['RH'].isnull() ] = value_to_fill_embarked

value_to_fill_embarked = train_df['AH'].dropna().mean()
train_df['AH'][ train_df['AH'].isnull() ] = value_to_fill_embarked


test_df = pd.read_csv("C:\\Users\\HP\\test_pollution.csv")
test_df = test_df.replace(-200, np.NaN)
value_to_fill_embarked = test_df['CO(GT)'].dropna().mean()
test_df['CO(GT)'][ test_df['CO(GT)'].isnull() ] = value_to_fill_embarked

value_to_fill_embarked = test_df['PT08.S1(CO)'].dropna().mean()
test_df['PT08.S1(CO)'][ test_df['PT08.S1(CO)'].isnull() ] = value_to_fill_embarked


test_df.drop('NMHC(GT)', axis=1, inplace=True)

value_to_fill_embarked = test_df['C6H6(GT)'].dropna().mean()
test_df['C6H6(GT)'][ test_df['C6H6(GT)'].isnull() ] = value_to_fill_embarked

value_to_fill_embarked = test_df['PT08.S2(NMHC)'].dropna().mean()
test_df['PT08.S2(NMHC)'][test_df['PT08.S2(NMHC)'].isnull() ] = value_to_fill_embarked

value_to_fill_embarked = test_df['NOx(GT)'].dropna().mean()
test_df['NOx(GT)'][ test_df['NOx(GT)'].isnull() ] = value_to_fill_embarked


value_to_fill_embarked = test_df['PT08.S3(NOx)'].dropna().mean()
test_df['PT08.S3(NOx)'][ test_df['PT08.S3(NOx)'].isnull() ] = value_to_fill_embarked

value_to_fill_embarked = test_df['NO2(GT)'].dropna().mean()
test_df['NO2(GT)'][ test_df['NO2(GT)'].isnull() ] = value_to_fill_embarked

value_to_fill_embarked = test_df['PT08.S4(NO2)'].dropna().mean()
test_df['PT08.S4(NO2)'][ test_df['PT08.S4(NO2)'].isnull() ] = value_to_fill_embarked

value_to_fill_embarked = test_df['PT08.S5(O3)'].dropna().mean()
test_df['PT08.S5(O3)'][ test_df['PT08.S5(O3)'].isnull() ] = value_to_fill_embarked

value_to_fill_embarked = test_df['T'].dropna().mean()
test_df['T'][ test_df['T'].isnull() ] = value_to_fill_embarked

value_to_fill_embarked = test_df['RH'].dropna().mean()
test_df['RH'][ test_df['RH'].isnull() ] = value_to_fill_embarked

value_to_fill_embarked = test_df['AH'].dropna().mean()
test_df['AH'][ test_df['AH'].isnull() ] = value_to_fill_embarked
#PT08.S4(NO2)
features = train_df
features = features.drop('Date', axis=1)
features = features.drop('Time', axis=1)
features = features.drop('NO2(GT)', axis=1)
features = features.drop('CO(GT)', axis=1)
#features = features.drop('PT08.S2(NMHC)', axis=1)
#features = features.drop('PT08.S5(O3)', axis=1)
features = features.drop('C6H6(GT)', axis=1)

labels_C6H6=train_df['C6H6(GT)']
labels_CO=train_df['CO(GT)']
labels_PT=train_df['PT08.S5(O3)']
labels_NO2=train_df['NO2(GT)']
features = features.values

#print('CO_mean: %.3f' % (train_df['CO(GT)'].dropna().mean()))
#print('NO2_mean: %.3f' % (train_df['NO2(GT)'].dropna().mean()))
#print('C6H6_mean: %.3f' % (train_df['C6H6(GT)'].dropna().mean()))

features_test = test_df
features_test = features_test.drop('Date', axis=1)
features_test = features_test.drop('Time', axis=1)
features_test = features_test.drop('NO2(GT)', axis=1)
features_test = features_test.drop('CO(GT)', axis=1)

#features_test = features_test.drop('PT08.S5(O3)', axis=1)
features_test = features_test.drop('C6H6(GT)', axis=1)


labels2_C6H6=test_df['C6H6(GT)']
labels2_CO=test_df['CO(GT)']
labels2_PT=test_df['PT08.S5(O3)']
labels2_NO2=test_df['NO2(GT)']
features_test=features_test.values

#print('CO_mean_test: %.3f' % (test_df['CO(GT)'].dropna().mean()))
#print('NO2_mean_test: %.3f' % (test_df['NO2(GT)'].dropna().mean()))
#print('C6H6_mean_test: %.3f' % (test_df['C6H6(GT)'].dropna().mean()))


scaler= StandardScaler().fit(features)
features = scaler.transform(features)  
features_test = scaler.transform(features_test)

#labels=labels.astype('int')
#labels_test=labels_test.astype('int')

from sklearn.metrics import r2_score,mean_squared_error
from sklearn.svm import SVC
from sklearn.ensemble import RandomForestClassifier
from sklearn.ensemble import RandomForestRegressor
from sklearn.neural_network import MLPClassifier
from sklearn.naive_bayes import GaussianNB
from sklearn.neural_network import MLPClassifier
from sklearn.svm import SVR
from sklearn.metrics import r2_score,mean_squared_error
support_regressor = SVR(kernel='rbf',gamma='scale', C=1.0, epsilon=0.2)
support_regressor2 = SVR(kernel='rbf', C=1000)
support_regressor3 = SVR(kernel='rbf', C=1000)
reg=RandomForestRegressor(random_state=0,max_depth=20,n_estimators=100)
reg2=RandomForestRegressor(random_state=0,max_depth=20,n_estimators=100)
reg3=RandomForestRegressor(random_state=0,max_depth=20,n_estimators=100)
reg4=RandomForestRegressor(random_state=0,max_depth=20,n_estimators=100)
mlp = MLPClassifier(hidden_layer_sizes=(10, 10, 10), max_iter=1000)
gnb = GaussianNB()
reg=reg.fit(features,labels_NO2)
y_train_pred=reg.predict(features)
y_pred =reg.predict(features_test)

reg2=reg2.fit(features,labels_CO)
y_train_pred_CO =reg2.predict(features)
y_pred_CO =reg2.predict(features_test)

#reg3 =reg3.fit(features,labels_PT)
#y_train_pred_PT08= reg3.predict(features)
#y_pred_PT08 = reg3.predict(features_test)
reg3 =reg3.fit(features,labels_C6H6)
y_train_pred_C6H6=reg3.predict(features)
y_pred_C6H6 = reg3.predict(features_test)

pd.DataFrame(data={'Date':test_df.Date,'time':test_df.Time,'NO2': y_pred,'CO':y_pred_CO,'C6H6':y_pred_C6H6}).to_csv('C:\\Users\\HP\\output_final3.csv',index=False)
#print('NO2_mean_pred: %.3f' % (y_pred.mean()))
#print('CO_mean_pred: %.3f' % (y_pred_CO.mean()))
#print('C6H6_mean_pred: %.3f' % (y_train_pred_C6H6.mean()))
#y_pred=y_pred.astype('int')
#y_pred_PT08 =y_pred_PT08.astype('int')
print('r2_score train: %.3f, test: %.3f' % (
        r2_score(labels_NO2, y_train_pred),
        r2_score(labels2_NO2, y_pred)))

print('r2_score train: %.3f, test: %.3f' % (
        r2_score(labels_CO, y_train_pred_CO),
        r2_score(labels2_CO, y_pred_CO)))

#print('r2_score train: %.3f, test: %.3f' % (
 #       r2_score(labels_PT, y_train_pred_PT08),
  #      r2_score(labels2_PT, y_pred_PT08)))

print('r2_score train: %.3f, test: %.3f' % (
        r2_score(labels_C6H6, y_train_pred_C6H6),
        r2_score(labels2_C6H6, y_pred_C6H6)))

import matplotlib.pyplot as plt; plt.rcdefaults()
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.pyplot as plt1 
from http.server import HTTPServer, BaseHTTPRequestHandler

from io import BytesIO
class SimpleHTTPRequestHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.end_headers()
        with open('C:\\Users\\HP\\output_final3.csv', 'rb') as file: 
            self.wfile.write(file.read())
        
    def do_POST(self):
        content_length = int(self.headers['Content-Length'])
        body = self.rfile.read(content_length)
        self.send_response(200)
        self.end_headers()
        response = BytesIO()
        response.write(b'This is POST request. ')
        response.write(b'Received: ')
        response.write(body)
        self.wfile.write(response.getvalue())
httpd = HTTPServer(('192.168.100.10', 9000), SimpleHTTPRequestHandler)      

#httpd = HTTPServer(('localhost', 9000), SimpleHTTPRequestHandler)
httpd.serve_forever()


Scenario: Should has Banyan Tree Hangzhou at Hangzhou city on Feb 01, 2015

Given ผู้ใช้ไปที่ http://www.agoda.com

When user enter <city> into SearchInput
And หยุดรอ 10 วินาที
And user select text <checkin_month> at CheckInMonthYear dropdown
And user select text <checkin_day> at CheckInDay dropdown
And user select text <night> at NightCount dropdown
And user click class submit
Then system display page as /pages/agoda/default/DestinationSearchResult.aspx
And there is hotel <hotelname> in hotelInfoPlaceholder

When user clickhotel <hotelname>
And user select currency Thai Baht (THB) on class sl_choosesearch
And user clicklink class show_moreroom

Examples: 
|city|hotelname|checkin_month|checkin_day|night|
|Hangzhou|Banyan Tree Hangzhou|Feb, 2015|Sun 01|1|

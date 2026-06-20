SELECT *
FROM JOB_HISTORY
WHERE JOB_ID IN
(
    'AD_ASST',
    'FI_MGR',
    'FI_ACCOUNT',
    'AC_MGR',
    'AC_ACCOUNT',
    'SA_MAN',
    'SA_REP',
    'PU_MAN'
)
AND DEPARTMENT_ID IN
(
    SELECT DEPARTMENT_ID
    FROM DEPARTMENTS
    WHERE DEPARTMENT_NAME IN
    (
        'Administration',
        'Marketing',
        'Purchasing',
        'Human Resources',
        'Shipping'
    )
);